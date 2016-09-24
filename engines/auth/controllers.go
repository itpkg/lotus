package auth

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

func (p *Engine) getHome(c *gin.Context) {
	c.HTML(http.StatusOK, "home", gin.H{})
}

//Mount mout
func (p *Engine) Mount(rt *gin.Engine) {
	rt.GET("/", p.getHome)

}
