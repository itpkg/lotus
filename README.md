# LOTUS - A web application

## Development

### Clone code

```
git clone https://github.com/itpkg/lotus.git
cd lotus
# start backend server
cd app && gradle bootRun
# start front server
npm install
npm start
```

## Deployment

```
make clean
make
cd release
ls config # edit configuration file
./run.sh
```
