package main

import (
	"log"

	_ "github.com/itpkg/lotus/engines/blog"
	_ "github.com/itpkg/lotus/engines/platform"
	_ "github.com/itpkg/lotus/engines/reading"
	"github.com/itpkg/lotus/web"
)

var version string

func main() {
	if err := web.Main(version); err != nil {
		log.Fatal(err)
	}
}
