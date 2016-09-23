/**
http://wiki2.dovecot.org/HowTo/DovecotPostgresql
https://www.linode.com/docs/email/postfix/email-with-postfix-dovecot-and-mysql
http://www.tunnelsup.com/using-salted-sha-hashes-with-dovecot-authentication
*/
package mail

import (
	"github.com/facebookgo/inject"
	"github.com/gin-gonic/gin"
	"github.com/itpkg/lotus/web"
	"github.com/jinzhu/gorm"
	"github.com/urfave/cli"
)

type Engine struct {
}

func (p *Engine) Map(*inject.Graph) {

}

func (p *Engine) Mount(*gin.Engine) {

}

func (p *Engine) Migrate(*gorm.DB) {}

func (p *Engine) Seed() {}

func (p *Engine) Worker() {}

func (p *Engine) Shell() []cli.Command {
	return []cli.Command{}
}

func init() {
	web.Register(&Engine{})
}
