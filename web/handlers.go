package web

import (
	"net/http"

	"github.com/go-martini/martini"
	"github.com/spf13/viper"
	"golang.org/x/text/language"
)

//LangHandler get locale from url, cookie, header
func LangHandler(tags ...language.Tag) martini.Handler {
	matcher := language.NewMatcher(tags)

	return func(ctx martini.Context, req *http.Request) {
		const key = "locale"
		// 1. Check URL arguments.
		lng := req.URL.Query().Get(key)

		// 2. Get language information from cookies.
		if len(lng) == 0 {
			if ck, er := req.Cookie("locale"); er == nil {
				lng = ck.Value
			}
		}

		// 3. Get language information from 'Accept-Language'.
		if len(lng) == 0 {
			al := req.Header.Get("Accept-Language")
			if len(al) > 4 {
				lng = al[:5]
			}
		}

		tag, _, _ := matcher.Match(language.Make(lng))
		ctx.Map(tag)
	}
}

func init() {
	viper.SetDefault("locales", []string{
		language.SimplifiedChinese.String(),
		language.AmericanEnglish.String(),
	})
}
