package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type CmsComments_20160920_223952 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &CmsComments_20160920_223952{}
	m.Created = "20160920_223952"
	migration.Register("CmsComments_20160920_223952", m)
}

// Run the migrations
func (m *CmsComments_20160920_223952) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *CmsComments_20160920_223952) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
