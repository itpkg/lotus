package web

import (
	"crypto/x509/pkix"
	"fmt"
	"html/template"
	"log"
	"os"
	"path"

	"github.com/BurntSushi/toml"
	"github.com/go-martini/martini"
	"github.com/spf13/viper"
	"github.com/urfave/cli"
)

//IocAction action with objects injected.
func IocAction(f func(*cli.Context, *martini.ClassicMartini) error) cli.ActionFunc {
	return Action(func(ctx *cli.Context) error {
		if err := viper.ReadInConfig(); err != nil {
			return err
		}
		mrt := martini.Classic()
		for _, en := range engines {
			args, err := mrt.Invoke(en.Init())
			if err != nil {
				return err
			}
			if len(args) > 0 {
				err = args[len(args)-1].Interface().(error)
				return err
			}
		}
		return f(ctx, mrt)
	})
}

//Action action with config load
func Action(f cli.ActionFunc) cli.ActionFunc {
	return func(c *cli.Context) error {
		if err := viper.ReadInConfig(); err != nil {
			return err
		}
		return f(c)
	}
}

//Main app entry
func Main(version string) error {
	app := cli.NewApp()
	app.Name = os.Args[0]
	app.Version = version
	app.Usage = "IT-PACKAGE web application(by go-lang)."
	app.EnableBashCompletion = true
	app.Commands = []cli.Command{
		{
			Name:    "init",
			Aliases: []string{"i"},
			Usage:   "init config file",
			Flags: []cli.Flag{
				cli.StringFlag{
					Name:  "environment, e",
					Value: "development",
					Usage: "environment, like: development, production, test...",
				},
			},
			Action: func(c *cli.Context) error {
				const fn = "config.toml"
				if _, err := os.Stat(fn); err == nil {
					return fmt.Errorf("file %s already exists", fn)
				}
				fmt.Printf("generate file %s\n", fn)

				viper.Set("env", c.String("environment"))
				args := viper.AllSettings()
				fd, err := os.Create(fn)
				if err != nil {
					log.Println(err)
					return err
				}
				defer fd.Close()
				end := toml.NewEncoder(fd)
				err = end.Encode(args)
				return err
			},
		},
		{
			Name:    "server",
			Aliases: []string{"s"},
			Usage:   "start the app server",
			Action: IocAction(func(_ *cli.Context, mrt *martini.ClassicMartini) error {
				for _, en := range engines {
					en.Mount(mrt)
				}
				mrt.RunOnAddr(fmt.Sprintf(":%d", viper.GetInt("http.port")))
				return nil
			}),
		},
		{
			Name:    "worker",
			Aliases: []string{"w"},
			Usage:   "start the worker progress",
			Action: func(*cli.Context) error {
				//TODO
				return nil
			},
		},
		{
			Name:    "openssl",
			Aliases: []string{"ssl"},
			Usage:   "generate ssl certificates",
			Flags: []cli.Flag{
				cli.StringFlag{
					Name:  "name, n",
					Usage: "name",
				},
				cli.StringFlag{
					Name:  "country, c",
					Value: "Earth",
					Usage: "country",
				},
				cli.StringFlag{
					Name:  "organization, o",
					Value: "Mother Nature",
					Usage: "organization",
				},
				cli.IntFlag{
					Name:  "years, y",
					Value: 1,
					Usage: "years",
				},
			},
			Action: func(c *cli.Context) error {
				name := c.String("name")
				if len(name) == 0 {
					cli.ShowCommandHelp(c, "openssl")
					return nil
				}
				root := path.Join("etc", "ssl", name)

				key, crt, err := CreateCertificate(
					true,
					pkix.Name{
						Country:      []string{c.String("country")},
						Organization: []string{c.String("organization")},
					},
					c.Int("years"),
				)
				if err != nil {
					return err
				}

				fnk := path.Join(root, "key.pem")
				fnc := path.Join(root, "crt.pem")

				fmt.Printf("generate pem file %s\n", fnk)
				err = WritePemFile(fnk, "RSA PRIVATE KEY", key)
				fmt.Printf("test: openssl rsa -noout -text -in %s\n", fnk)

				if err == nil {
					fmt.Printf("generate pem file %s\n", fnc)
					err = WritePemFile(fnc, "CERTIFICATE", crt)
					fmt.Printf("test: openssl x509 -noout -text -in %s\n", fnc)
				}
				if err == nil {
					fmt.Printf(
						"verify: diff <(openssl rsa -noout -modulus -in %s) <(openssl x509 -noout -modulus -in %s)\n",
						fnk,
						fnc,
					)
				}

				return err
			},
		},
		{
			Name:    "nginx",
			Aliases: []string{"ng"},
			Usage:   "init nginx config file",
			Action: Action(func(*cli.Context) error {
				const tpl = `
upstream {{.Domain}}_prod {
  server localhost:{{.Port}} fail_timeout=0;
}

server {
  listen {{if .Ssl}}443{{- else}}80{{- end}};

{{if .Ssl}}
  ssl  on;
  ssl_certificate  ssl/{{.Domain}}-cert.pem;
  ssl_certificate_key  ssl/{{.Domain}}-key.pem;
  ssl_session_timeout  5m;
  ssl_protocols  SSLv2 SSLv3 TLSv1;
  ssl_ciphers  RC4:HIGH:!aNULL:!MD5;
  ssl_prefer_server_ciphers  on;
{{- end}}

  client_max_body_size 4G;
  keepalive_timeout 10;

  proxy_buffers 16 64k;
  proxy_buffer_size 128k;

  server_name {{.Domain}};

  access_log /var/log/nginx/{{.Domain}}.access.log;
  error_log /var/log/nginx/{{.Domain}}.error.log;

  location / {
    {{if .Ssl}}proxy_set_header X-Forwarded-Proto https;{{- end}}
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header Host $http_host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_redirect off;
    proxy_pass http://{{.Domain}}_prod/$2$is_args$args;
    # limit_req zone=one;
  }

}

`
				t, err := template.New("").Parse(tpl)
				if err != nil {
					return err
				}
				pwd, err := os.Getwd()
				if err != nil {
					return err
				}

				domain := viper.GetString("http.host")
				fn := path.Join("etc", "nginx", "sites-enabled", domain+".conf")
				if err = os.MkdirAll(path.Dir(fn), 0700); err != nil {
					return err
				}
				fmt.Printf("generate file %s\n", fn)
				fd, err := os.OpenFile(fn, os.O_WRONLY|os.O_CREATE|os.O_EXCL, 0600)
				if err != nil {
					return err
				}
				defer fd.Close()

				return t.Execute(fd, struct {
					Domain  string
					Port    int
					Ssl     bool
					Root    string
					Version string
				}{
					Ssl:     viper.GetBool("http.ssl"),
					Domain:  domain,
					Port:    viper.GetInt("http.port"),
					Root:    pwd,
					Version: "v1",
				})
			}),
		},
	}

	for _, en := range engines {
		cmd := en.Shell()
		app.Commands = append(app.Commands, cmd...)
	}

	return app.Run(os.Args)
}

func init() {
	viper.SetConfigName("config")
	viper.AddConfigPath(".")

	viper.SetDefault("http", map[string]interface{}{
		"host": "www.change-me.com",
		"port": 8080,
		"ssl":  false,
	})
}
