# lotus

A web application by go-lang.

## Install go

### ubuntu

```
add-apt-repository ppa:ubuntu-lxc/lxd-stable
sudo apt-get update
sudo apt-get install golang
```

### add to your .bashrc

```
GOPATH=$HOME/go
PATH=$GOPATH/bin:$PATH
export GOPATH PATH
```

## Database creation

### postgresql

```
psql -U postgres
CREATE DATABASE db-name WITH ENCODING = 'UTF8';
CREATE USER user-name WITH PASSWORD 'change-me';
GRANT ALL PRIVILEGES ON DATABASE db-name TO user-name;
```

## Build

```
go get github.com/itpkg/lotus
cd $github.com/itpkg/lotus/demo
make
ls dist
```

## Editor

### Atom

- go-plus
