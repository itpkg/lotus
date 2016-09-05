package platform

import (
	"github.com/codegangsta/inject"
	"github.com/itpkg/lotus/web"
)

//Map map objects
func (p *Engine) Map(inj inject.Injector) error {
	db, err := web.OpenDatabase()
	if err != nil {
		return err
	}
	inj.Map(db)
	return nil
}
