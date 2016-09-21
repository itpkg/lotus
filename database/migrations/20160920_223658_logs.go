package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type Logs_20160920_223658 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &Logs_20160920_223658{}
	m.Created = "20160920_223658"
	migration.Register("Logs_20160920_223658", m)
}

// Run the migrations
func (m *Logs_20160920_223658) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *Logs_20160920_223658) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
