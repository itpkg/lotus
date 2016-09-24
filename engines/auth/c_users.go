package auth

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

func (p *Engine) postUserSignIn(c *gin.Context) {
	//TODO
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
