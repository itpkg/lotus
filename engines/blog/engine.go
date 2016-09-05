package blog

import (
	"github.com/codegangsta/inject"
	"github.com/itpkg/lotus/web"
	"github.com/jinzhu/gorm"
)

//Map map objects
func (p *Engine) Map(inject.Injector) error {
	return nil
}

//Migrate db migrate
func (p *Engine) Migrate(*gorm.DB) {

}

//Engine blos's engine
type Engine struct {
}

func init() {
	web.Register(&Engine{})
}
