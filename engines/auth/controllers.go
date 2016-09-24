package auth

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
	"golang.org/x/text/language"
)

func (p *Engine) getInfo(c *gin.Context) {
	lng := c.MustGet("locale").(*language.Tag)
	rst := make(map[string]interface{})
	for _, k := range []string{"title", "subTitle", "keywords", "description", "copyright"} {
		var v string
		if err := p.Dao.Get(fmt.Sprintf("%s://site/%s", lng.String(), k), &v); err != nil {
			p.Logger.Error(err)
		}
		rst[k] = v
	}
	author := make(map[string]string)
	for _, k := range []string{"user", "email"} {
		var v string
		p.Dao.Get(fmt.Sprintf("//site/author/%s", k), &v)
		author[k] = v
	}
	rst["author"] = author
	rst["locale"] = lng.String()
	c.JSON(http.StatusOK, rst)
}

func (p *Engine) postUserSignIn(c *gin.Context) {
	var fm FmSignIn
	if err := c.BindJSON(&fm); err != nil {
		c.String(http.StatusInternalServerError, err.Error())
	}

}
func (p *Engine) postUserSignUp(c *gin.Context) {
	//TODO
}
func (p *Engine) postUserConfirm(c *gin.Context) {
	//TODO
}
func (p *Engine) postUserUnlock(c *gin.Context) {
	//TODO
}
func (p *Engine) postUserForgotPassword(c *gin.Context) {
	//TODO
}
func (p *Engine) postUserResetPassword(c *gin.Context) {
	//TODO
}

//Mount mout
func (p *Engine) Mount(rt *gin.Engine) {
	rt.GET("/info", p.getInfo)

	ug := rt.Group("/users")
	ug.POST("/sign_in", p.postUserSignIn)
	ug.POST("/sign_up", p.postUserSignUp)
	ug.POST("/confirm", p.postUserConfirm)
	ug.POST("/unlock", p.postUserUnlock)
	ug.POST("/forgot_password", p.postUserForgotPassword)
	ug.POST("/reset_password", p.postUserResetPassword)
}
