# lotus

A web application(by c++)

## Prepare database

```
psql -U postgres
CREATE DATABASE db-name WITH ENCODING = 'UTF8';
CREATE USER user-name WITH PASSWORD 'change-me';
GRANT ALL PRIVILEGES ON DATABASE db-name TO user-name;
```

## Build
```
git clone https://github.com/itpkg/lotus.git
cd lotus
mkdir build
cd build
cmake -DCMAKE_BUILD_TYPE=Release ..
make
make install
```

## Deployment

## Atom plugs
* [linter-clang](https://atom.io/packages/linter-clang)
* [autocomplete-clang](https://atom.io/packages/autocomplete-clang)
* [language-cmake](https://atom.io/packages/language-cmake)

## Documents
* [Google C++ Style Guide](https://google.github.io/styleguide/cppguide.html)
