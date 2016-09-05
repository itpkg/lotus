package reading

import (
	"github.com/itpkg/lotus/web"
	"github.com/urfave/cli"
)

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
