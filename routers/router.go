package routers

import (
	"github.com/itpkg/lotus/controllers"
	"github.com/astaxie/beego"
)

func init() {
    beego.Router("/", &controllers.MainController{})
}
