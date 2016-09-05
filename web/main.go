package web

import (
	"crypto/x509/pkix"
	"fmt"
	"log"
	"os"
	"path"

	"github.com/BurntSushi/toml"
	"github.com/spf13/viper"
	"github.com/urfave/cli"
)

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
			Action: func(*cli.Context) error {
				//TODO
				return nil
			},
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
	}

	for _, en := range engines {
		cmd := en.Shell()
		app.Commands = append(app.Commands, cmd...)
	}

	return app.Run(os.Args)
}

func init() {
	viper.SetDefault("http", map[string]interface{}{
		"host": "www.change-me.com",
		"port": 8080,
		"ssl":  false,
	})
}
