package platform

import (
	"github.com/go-martini/martini"
	"github.com/itpkg/lotus/web"
	"github.com/spf13/viper"
	"github.com/urfave/cli"
)

//Engine platform's engine
type Engine struct {
}

//Init init mapped objects
func (p *Engine) Init() martini.Handler {
	return func() {

	}
}

//Mount mount web endpoints
func (p *Engine) Mount(martini.Router) {

}

//Shell shell commands
func (p *Engine) Shell() []cli.Command {
	return []cli.Command{}
}

func init() {
	viper.SetDefault("database", map[string]interface{}{
		"host":     "localhost",
		"port":     5432,
		"name":     "lotus_dev",
		"user":     "lotus",
		"password": "",
		"ssl":      false,
	})
	viper.SetDefault("cache", map[string]interface{}{
		"driver": "redis",
		"host":   "localhost",
		"port":   6379,
		"db":     2,
	})
	web.Register(&Engine{})
}
