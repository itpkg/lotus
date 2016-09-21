package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type CmsTags_20160920_223949 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &CmsTags_20160920_223949{}
	m.Created = "20160920_223949"
	migration.Register("CmsTags_20160920_223949", m)
}

// Run the migrations
func (m *CmsTags_20160920_223949) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *CmsTags_20160920_223949) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
