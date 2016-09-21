package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type Settings_20160920_223727 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &Settings_20160920_223727{}
	m.Created = "20160920_223727"
	migration.Register("Settings_20160920_223727", m)
}

// Run the migrations
func (m *Settings_20160920_223727) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *Settings_20160920_223727) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
