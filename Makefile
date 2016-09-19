dst=release

build:
	mkdir -p $(dst)/config

	cd api && gradle build
	cp api/app/build/libs/lotus-app-*.jar $(dst)
	cp api/app/src/main/resources/application.properties $(dst)/config/
	cp api/app/src/main/resources/logback-file.xml $(dst)/config/logback.xml

	cd front && npm run build
	cp -r front/dist  $(dst)/public

	cp run.sh $(dst)

clean:
	cd api && gradle clean
	-rm -r $(dst)
