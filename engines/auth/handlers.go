package auth

import (
	"fmt"

	"github.com/gin-gonic/gin"
	logging "github.com/op/go-logging"
	"golang.org/x/text/language"
)

//Page page info
type Page struct {
	Locale      string
	Title       string
	SubTitle    string
	Keywords    string
	Description string
	Copyright   string

	Home   string
	Author Author
	Links  []Link
}

//Author author info
type Author struct {
	Name  string
	Email string
}

//Link link info
type Link struct {
	Title string
	Href  string
}

//PageHandler page info handler
func PageHandler(dao *Dao, logger *logging.Logger) gin.HandlerFunc {
	return func(c *gin.Context) {
		locale := c.MustGet("locale").(*language.Tag).String()
		var page Page
		if err := dao.Get(fmt.Sprintf("%s://site/info", locale), &page); err != nil {
			logger.Error(err)
			page.Locale = locale
		}
		c.Set("page", &page)
	}
}
