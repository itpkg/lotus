package auth

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

func (p *Engine) getHome(c *gin.Context) {
	page := c.MustGet("page").(*Page)
	if page.Home == "" {
		var notices []Notice
		if err := p.Db.Order("updated_at DESC").Find(&notices).Error; err != nil {
			p.Logger.Error(err)
		}
		c.HTML(http.StatusOK, "home", gin.H{"page": page, "notices": notices})
	} else {
		c.Redirect(http.StatusTemporaryRedirect, page.Home)
	}
}
