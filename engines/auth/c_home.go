package auth

import (
	"fmt"

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

func (p *Engine) getInfo(c *gin.Context) (interface{}, error) {
	locale := c.MustGet("locale").(*language.Tag).String()
	var page Page
	err := p.Dao.Get(fmt.Sprintf("%s://site/info", locale), &page)
	return page, err
}
