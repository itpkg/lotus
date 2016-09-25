LOTUS - A web application
---


## Prepare database

```
psql -U postgres
CREATE DATABASE db-name WITH ENCODING = 'UTF8';
CREATE USER user-name WITH PASSWORD 'change-me';
GRANT ALL PRIVILEGES ON DATABASE db-name TO user-name;
```

## Development

### Clone code
```
git clone https://github.com/itpkg/lotus.git
cd lotus
```


### Start backend server
```
cd app 
gradle bootRun
```

### start front server
```
npm install
bower install
ember s
```


## Deployment

```
make clean
make
cd dist
ls config # edit configuration file
./run.sh

firefox http://your-hostname:8080/install
```
