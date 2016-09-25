package auth

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

//JSON json handler
func JSON(fn func(*gin.Context) (interface{}, error)) gin.HandlerFunc {
	return func(c *gin.Context) {
		if val, err := fn(c); err == nil {
			c.JSON(http.StatusOK, val)
		} else {
			// c.AbortWithError(http.StatusInternalServerError, err)
			c.String(http.StatusInternalServerError, err.Error())
		}
	}
}
