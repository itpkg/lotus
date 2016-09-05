package blog

import (
	"fmt"
	"net/http"

	"github.com/go-martini/martini"
	"github.com/martini-contrib/render"
	"golang.org/x/text/language"
)

func show(lng language.Tag, rdr render.Render, pms martini.Params) {
	name := pms["_1"]
	if name == "" {
		name = fmt.Sprintf("%s.md", lng.String())
	}

	rdr.HTML(http.StatusOK, "blog", struct {
		Site Site
	}{Site: Site{
		Title:    name,
		SubTitle: "SubTitle",
	}})
}

//Mount mount web endpoints
func (p *Engine) Mount(r martini.Router) {
	r.Get("/blog/**", show)
}
