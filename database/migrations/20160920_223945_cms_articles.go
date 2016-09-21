package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type CmsArticles_20160920_223945 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &CmsArticles_20160920_223945{}
	m.Created = "20160920_223945"
	migration.Register("CmsArticles_20160920_223945", m)
}

// Run the migrations
func (m *CmsArticles_20160920_223945) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *CmsArticles_20160920_223945) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
