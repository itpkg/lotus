package main

import (
	"github.com/astaxie/beego"
	_ "github.com/astaxie/beego/cache/redis"
	_ "github.com/itpkg/lotus/routers"
	_ "github.com/lib/pq"
)

func main() {
	beego.Run()
}
