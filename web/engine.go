package web

import (
	"github.com/codegangsta/inject"
	"github.com/go-martini/martini"
	"github.com/jinzhu/gorm"
	"github.com/urfave/cli"
)

//Engine web engine
type Engine interface {
	Map(inject.Injector) error
	Mount(martini.Router)
	Migrate(*gorm.DB)
	Shell() []cli.Command
}

var engines []Engine

//Register register engine
func Register(ens ...Engine) {
	engines = append(engines, ens...)
}
