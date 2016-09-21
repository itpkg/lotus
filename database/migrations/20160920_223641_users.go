package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type Users_20160920_223641 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &Users_20160920_223641{}
	m.Created = "20160920_223641"
	migration.Register("Users_20160920_223641", m)
}

// Run the migrations
func (m *Users_20160920_223641) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *Users_20160920_223641) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
