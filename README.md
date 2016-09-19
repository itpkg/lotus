# LOTUS - A web application


## Development

### Clone code
    git clone https://github.com/itpkg/lotus.git
    cd lotus

### Api server
    cd app && gradle bootRun
    
### Front server
    npm install
    npm start
    
## Build
    gradle clean
    gradle build
    npm run build
    mkdir -p release/config
    cp app/build/libs/lotus-app-*.jar release/
    cp app/src/main/resources/application.properties release/config/
    cp app/src/main/resources/logback-file.xml release/config/logback.xml
    cp -r dist release/public