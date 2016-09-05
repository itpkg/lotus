package platform

import (
	"github.com/go-martini/martini"
	"github.com/itpkg/lotus/web"
	"github.com/jinzhu/gorm"
	"github.com/spf13/viper"
)

//Engine platform's engine
type Engine struct {
}

//Migrate db migrate
func (p *Engine) Migrate(*gorm.DB) {

}

//Mount mount web endpoints
func (p *Engine) Mount(martini.Router) {

}

func init() {
	viper.SetDefault("cache", map[string]interface{}{
		"driver": "redis",
		"host":   "localhost",
		"port":   6379,
		"db":     2,
	})
	web.Register(&Engine{})
}
