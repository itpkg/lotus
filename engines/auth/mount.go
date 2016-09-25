package auth

import "github.com/gin-gonic/gin"

//Mount mout
func (p *Engine) Mount(rt *gin.Engine) {
	rt.GET("/info", JSON(p.getInfo))
	rt.POST("/install", JSON(p.postInstall))

	ug := rt.Group("/users")
	ug.POST("/sign-in", JSON(p.postUserSignIn))
	ug.POST("/sign-up", JSON(p.postUserSignUp))
	ug.POST("/confirm", JSON(p.postUserConfirm))
	ug.POST("/unlock", JSON(p.postUserUnlock))
	ug.POST("/forgot-password", JSON(p.postUserForgotPassword))
	ug.POST("/reset-password", JSON(p.postUserChangePassword))
	ug.PATCH("/confirm", JSON(p.patchUserConfirm))
	ug.PATCH("/unlock", JSON(p.patchUserUnlock))
}
