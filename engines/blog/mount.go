package blog

import (
	"net/http"

	"github.com/go-martini/martini"
	"github.com/martini-contrib/render"
	"golang.org/x/text/language"
)

func getIndex(lng language.Tag, pms martini.Params, rdr render.Render) {
	// name := pms["_1"]
	// if name == "" {
	// 	name = fmt.Sprintf("%s.md", lng.String())
	// }

	rdr.HTML(http.StatusOK, "blog", struct {
		Title string
	}{
		Title: "ttt",
	})
}

//Mount mount web endpoints
func (p *Engine) Mount(m martini.Router) {
	m.Get("/blog/**", getIndex)
}
