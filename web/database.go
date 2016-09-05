package web

import (
	"fmt"

	"github.com/jinzhu/gorm"
	"github.com/spf13/viper"
)

const postgresDriver = "postgres"

//OpenDatabase get database connection
func OpenDatabase() (*gorm.DB, error) {
	dbs := viper.GetStringMapString("database")
	drv := dbs["driver"]
	url := ""
	for k, v := range dbs {
		url += fmt.Sprintf(" %s=%s ", k, v)
	}
	return gorm.Open(drv, url)
}

func init() {

	viper.SetDefault("database", map[string]interface{}{
		"driver":   "postgres",
		"host":     "localhost",
		"port":     5432,
		"dbname":   "lotus_dev",
		"user":     "lotus",
		"password": RandomStr(16),
		"ssl":      false,
	})
}
