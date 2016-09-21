package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type Roles_20160920_223706 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &Roles_20160920_223706{}
	m.Created = "20160920_223706"
	migration.Register("Roles_20160920_223706", m)
}

// Run the migrations
func (m *Roles_20160920_223706) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *Roles_20160920_223706) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
