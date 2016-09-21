package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type Policies_20160920_223711 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &Policies_20160920_223711{}
	m.Created = "20160920_223711"
	migration.Register("Policies_20160920_223711", m)
}

// Run the migrations
func (m *Policies_20160920_223711) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *Policies_20160920_223711) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
