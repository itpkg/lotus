package auth

//FmSignIn sign-in form
type FmSignIn struct {
	Email    string `json:"email" binding:"required"`
	Password string `json:"password" binding:"required"`
}
