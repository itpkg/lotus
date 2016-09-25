dst=dist

build:
	mkdir -p $(dst)/config

	gradle build
	cp app/build/libs/lotus-app-*.jar $(dst)
	cp app/src/main/resources/application.properties $(dst)/config/
	cp app/src/main/resources/logback-file.xml $(dst)/config/logback.xml
	cp run.sh $(dst)

	cd front-ember && ember build --environment production
	-cp -rv front-ember/dist $(dst)/public


clean:
	gradle clean
	-rm -r $(dst)
