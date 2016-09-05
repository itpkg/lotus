package reading

import (
	"github.com/go-martini/martini"
	"github.com/itpkg/lotus/web"
	"github.com/urfave/cli"
)

//Init init mapped objects
func (p *Engine) Init() martini.Handler {
	return func() {

	}
}

//Mount mount web endpoints
func (p *Engine) Mount(martini.Router) {

}

//Engine reading's engine
type Engine struct {
}

//Shell shell commands
func (p *Engine) Shell() []cli.Command {
	return []cli.Command{}
}

func init() {
	web.Register(&Engine{})
}
