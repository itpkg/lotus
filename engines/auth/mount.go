package auth

import "github.com/gin-gonic/gin"

//Mount mout
func (p *Engine) Mount(rt *gin.Engine) {
	ph := PageHandler(p.Dao, p.Logger)
	rt.GET("/", ph, p.getHome)

	ug := rt.Group("/users")
	ug.POST("/sign_in", p.postUserSignIn)
	ug.POST("/sign_up", p.postUserSignUp)
	ug.POST("/confirm", p.postUserConfirm)
	ug.POST("/unlock", p.postUserUnlock)
	ug.POST("/forgot_password", p.postUserForgotPassword)
	ug.POST("/reset_password", p.postUserResetPassword)
}
