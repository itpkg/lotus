package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type ReadingNotes_20160920_224129 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &ReadingNotes_20160920_224129{}
	m.Created = "20160920_224129"
	migration.Register("ReadingNotes_20160920_224129", m)
}

// Run the migrations
func (m *ReadingNotes_20160920_224129) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update

}

// Reverse the migrations
func (m *ReadingNotes_20160920_224129) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update

}
