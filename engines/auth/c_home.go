package auth

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
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

func (p *Engine) getInfo(c *gin.Context) {
	locale := c.MustGet("locale").(*language.Tag).String()
	var page Page
	if err := p.Dao.Get(fmt.Sprintf("%s://site/info", locale), &page); err != nil {
		p.Logger.Error(err)
		page.Locale = locale
	}
	c.JSON(http.StatusOK, page)
}
