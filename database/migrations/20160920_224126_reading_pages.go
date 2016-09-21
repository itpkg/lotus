package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type ReadingPages_20160920_224126 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &ReadingPages_20160920_224126{}
	m.Created = "20160920_224126"
	migration.Register("ReadingPages_20160920_224126", m)
}

// Run the migrations
func (m *ReadingPages_20160920_224126) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *ReadingPages_20160920_224126) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
