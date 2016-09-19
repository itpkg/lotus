dst=release

build:
	mkdir -p $(dst)/config

	cd backend && gradle build
	cp backend/app/build/libs/lotus-app-*.jar $(dst)
	cp backend/app/src/main/resources/application.properties $(dst)/config/
	cp backend/app/src/main/resources/logback-file.xml $(dst)/config/logback.xml
	cp backend/run.sh $(dst)

	cd front && npm run build
	cp -r front/dist  $(dst)/public


clean:
	cd backend && gradle clean
	-rm -r $(dst)
