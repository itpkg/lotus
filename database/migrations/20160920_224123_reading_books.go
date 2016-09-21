package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type ReadingBooks_20160920_224123 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &ReadingBooks_20160920_224123{}
	m.Created = "20160920_224123"
	migration.Register("ReadingBooks_20160920_224123", m)
}

// Run the migrations
func (m *ReadingBooks_20160920_224123) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *ReadingBooks_20160920_224123) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
