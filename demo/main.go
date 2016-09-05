package main

import (
	_ "github.com/itpkg/lotus/engines/blog"
	_ "github.com/itpkg/lotus/engines/reading"
	"github.com/itpkg/lotus/web"
)

var version string

func main() {
	web.Main(version)
}
