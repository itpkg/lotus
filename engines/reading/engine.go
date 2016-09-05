package reading

import (
	"github.com/codegangsta/inject"
	"github.com/go-martini/martini"
	"github.com/itpkg/lotus/web"
	"github.com/jinzhu/gorm"
	"github.com/urfave/cli"
)

//Map map objects
func (p *Engine) Map(inject.Injector) error {
	return nil
}

//Migrate db migrate
func (p *Engine) Migrate(*gorm.DB) {

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
