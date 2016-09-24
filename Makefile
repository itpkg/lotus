dst=dist

build:
	go build -ldflags "-s -X main.version=`git rev-parse --short HEAD`" -o $(dst)/lotus demo/main.go
	-cp -rv demo/locales $(dst)/
	cd front-ember && ember build --environment production
	-cp -rv front-ember/dist $(dst)/public

clean:
	-rm -rv $(dst)
