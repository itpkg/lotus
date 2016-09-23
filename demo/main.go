package main

import (
	"log"

	_ "github.com/itpkg/lotus/engines/auth"
	_ "github.com/itpkg/lotus/engines/forum"
	_ "github.com/itpkg/lotus/engines/mail"
	_ "github.com/itpkg/lotus/engines/reading"
	_ "github.com/itpkg/lotus/engines/shop"
	_ "github.com/jinzhu/gorm/dialects/postgres"
	_ "github.com/lib/pq"

	"github.com/itpkg/lotus/web"
)

var version string

func main() {
	if err := web.Main(version); err != nil {
		log.Fatal(err)
	}
}