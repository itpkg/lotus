package web

import (
	"github.com/go-martini/martini"
	"github.com/urfave/cli"
)

//Engine web engine
type Engine interface {
	Init() martini.Handler
	Mount(martini.Router)
	Shell() []cli.Command
}

var engines []Engine

//Register register engine
func Register(ens ...Engine) {
	engines = append(engines, ens...)
}
