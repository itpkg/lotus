package web

import "github.com/urfave/cli"

//Engine web engine
type Engine interface {
	Shell() []cli.Command
}

var engines []Engine

//Register register engine
func Register(ens ...Engine) {
	engines = append(engines, ens...)
}
