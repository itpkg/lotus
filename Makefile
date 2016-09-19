dst=release

build:
	mkdir -p $(dst)/config

	gradle build
	cp app/build/libs/lotus-app-*.jar $(dst)
	cp app/src/main/resources/application.properties $(dst)/config/
	cp app/src/main/resources/logback-file.xml $(dst)/config/logback.xml

	npm run build
	cp -r dist  $(dst)/public

	cp run.sh $(dst)

clean:
	gradle clean
	-rm -r $(dst)
