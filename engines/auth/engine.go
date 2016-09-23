package auth

import (
	"crypto/aes"

	"github.com/SermoDigital/jose/crypto"
	"github.com/facebookgo/inject"
	"github.com/gin-gonic/gin"
	"github.com/itpkg/lotus/web"
	"github.com/jinzhu/gorm"
	"github.com/spf13/viper"
)

type Engine struct {
	Cache web.Cache `inject:""`
	Db    *gorm.DB  `inject:""`
	Dao   *Dao      `inject:""`
}

func (p *Engine) Map(inj *inject.Graph) error {

	cip, err := aes.NewCipher([]byte(viper.GetString("secrets.aes")))
	if err != nil {
		return err
	}

	return inj.Provide(
		&inject.Object{Value: cip},
		&inject.Object{Value: []byte(viper.GetString("secrets.jwt")), Name: "jwt.key"},
		&inject.Object{Value: crypto.SigningMethodHS512, Name: "jwt.method"},
	)

}

func (p *Engine) Mount(*gin.Engine) {

}

func (p *Engine) Migrate(db *gorm.DB) {
	db.AutoMigrate(
		&Setting{}, &Notice{}, &LeaveWord{},
		&User{}, &Role{}, &Permission{}, &Log{},
	)

	db.Model(&User{}).AddUniqueIndex("idx_user_provider_type_id", "provider_type", "provider_id")
	db.Model(&Role{}).AddUniqueIndex("idx_roles_name_resource_type_id", "name", "resource_type", "resource_id")
	db.Model(&Permission{}).AddUniqueIndex("idx_permissions_user_role", "user_id", "role_id")
}

func (p *Engine) Seed() {}

func (p *Engine) Worker() {}

func init() {
	web.Register(&Engine{})
}
