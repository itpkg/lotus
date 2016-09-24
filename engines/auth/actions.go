package auth

import (
	"github.com/facebookgo/inject"
	"github.com/itpkg/lotus/web"
	"github.com/spf13/viper"
	"github.com/urfave/cli"
)

//IocAction ioc action
func IocAction(fn func(*cli.Context, *inject.Graph) error) cli.ActionFunc {
	return Action(func(ctx *cli.Context) error {
		var inj inject.Graph
		logger := OpenLogger()
		if !IsProduction() {
			inj.Logger = logger
		}

		db, err := OpenDatabase()
		if err != nil {
			return err
		}
		rep := OpenRedis()

		if err := inj.Provide(
			&inject.Object{Value: logger},
			&inject.Object{Value: db},
			&inject.Object{Value: rep},
			&inject.Object{Value: &web.RedisCache{}},
			&inject.Object{Value: &web.RedisJob{
				Timeout:  viper.GetInt("workers.timeout"),
				Handlers: make(map[string]web.JobHandler),
			}},
		); err != nil {
			return err
		}
		web.Loop(func(en web.Engine) error {
			if e := en.Map(&inj); e != nil {
				return e
			}
			return inj.Provide(&inject.Object{Value: en})
		})
		if err := inj.Populate(); err != nil {
			return err
		}
		return fn(ctx, &inj)
	})
}

//Action load config action
func Action(f cli.ActionFunc) cli.ActionFunc {
	return func(c *cli.Context) error {
		if err := viper.ReadInConfig(); err != nil {
			return err
		}
		return f(c)
	}
}
