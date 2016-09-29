LOTUS - A web application(by Spring+Hibernate)
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
gradle build
```


## Documents
* [spring](http://docs.spring.io/spring/docs/5.0.0.M2/spring-framework-reference/htmlsingle/)
* [hibernate](http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html)
