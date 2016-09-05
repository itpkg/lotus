package main

import (
	"fmt"

	_ "github.com/itpkg/lotus/engines/blog"
	_ "github.com/itpkg/lotus/engines/reading"
)

var version string

func main() {
	fmt.Println(version)
}
