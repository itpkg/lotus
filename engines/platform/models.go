package platform

import (
	"time"

	"github.com/jinzhu/gorm"
)

//User user
type User struct {
	ID           uint `gorm:"primary_key"`
	Name         string
	Email        string
	ProviderID   string
	ProviderType string
	Lang         string
	CreatedAt    time.Time
	UpdatedAt    time.Time
}

//Permission permission
type Permission struct {
	gorm.Model
}
