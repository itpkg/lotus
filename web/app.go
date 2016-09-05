package web

import (
	"bytes"
	"fmt"
	"os"
	"text/template"

	docopt "github.com/docopt/docopt-go"
)

//Main app entry
func Main(version string) error {
	usage := `
A web application build by go-lang.

Usage:
 {{.Name}} init [--config=FILE]
 {{.Name}} start [--config=FILE] [--server] [--jobs=NUM]
 {{.Name}} db (create|console|migrate|seeds|rollback|drop) [--config=FILE]
 {{.Name}} cache (list|clear) [--config=FILE]
 {{.Name}} nginx [--config=FILE]
 {{.Name}} -h | --help
 {{.Name}} -v | --version

Options:
 -h --help      Show this screen.
 -v --version   Show version.
 --config=FILE  Config filename [default: config.toml].
 --jobs=NUM     Speed in knots [default: 2].
`

	var buf bytes.Buffer
	tpl, err := template.New("").Parse(usage)
	if err != nil {
		return err
	}
	if err = tpl.Execute(&buf, struct {
		Name string
	}{Name: os.Args[0]}); err != nil {
		return err
	}

	arguments, _ := docopt.Parse(buf.String(), nil, true, version, false)
	fmt.Println(arguments)
	return nil
}
