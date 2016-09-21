package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type Notices_20160920_224055 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &Notices_20160920_224055{}
	m.Created = "20160920_224055"
	migration.Register("Notices_20160920_224055", m)
}

// Run the migrations
func (m *Notices_20160920_224055) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *Notices_20160920_224055) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
