LOTUS - A web application.
---


## System dependencies
### Install rbenv
    sudo apt-get install -y git build-essential make libssl-dev libreadline-dev cmake libicu-dev sdcv

    git clone https://github.com/rbenv/rbenv.git ~/.rbenv
    git clone https://github.com/rbenv/ruby-build.git ~/.rbenv/plugins/ruby-build
    git clone https://github.com/rbenv/rbenv-vars.git ~/.rbenv/plugins/rbenv-vars

    # Modify your ~/.zshrc file instead of ~/.bash_profile
    echo 'export PATH=$HOME/.rbenv/bin:$PATH' >> ~/.bash_profile 
    echo 'eval "$(rbenv init -)"' >> ~/.bash_profile 
    
### Install ruby(by rbenv)
    rbenv install -l    
    CONFIGURE_OPTS="--disable-install-doc" rbenv install 2.3.1
    rbenv global 2.3.1
    gem install bundler
    
### Install nodejs
    curl -sL https://deb.nodesource.com/setup_6.x | sudo -E bash -
    sudo apt-get update
    sudo apt-get install -y nodejs
    
### Install jdk8
    sudo apt-add-repository ppa:webupd8team/java
    sudo apt-get update
    sudo apt-get install oracle-java8-installer
    
### Install elasticsearch
    wget -qO - https://packages.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -
    echo "deb https://packages.elastic.co/elasticsearch/5.x/debian stable main" | sudo tee -a /etc/apt/sources.list.d/elasticsearch-5.x.list
    sudo apt-get update 
    sudo apt-get install -y elasticsearch openjdk-7-jre
    
### Install tengine
    sudo apt-get install build-essential debhelper make autoconf automake patch dpkg-dev fakeroot pbuilder gnupg dh-make libssl-dev libpcre3-dev    
    git clone https://github.com/alibaba/tengine.git    
    cd tengine
    mv packages/debian .
    DEB_BUILD_OPTIONS=nocheck dpkg-buildpackage -rfakeroot -uc -b
    cd ..
    sudo dpkg -i tengine_2.1.0-1_amd64.deb
       
       
### Clone code
    git clone https://github.com/itpkg/reading.git
    cd reading
    bundler install
    npm install
    
## Database creation
    psql -U postgres
    CREATE DATABASE db-name WITH ENCODING = 'UTF8';
    CREATE USER user-name WITH PASSWORD 'change-me';
    GRANT ALL PRIVILEGES ON DATABASE db-name TO user-name;
    
## Configuration files

* .rbenv-vars
* config/database.yml
* config/sidekiq.yml
* config/deploy/production.rb

## Deployment instructions
### deploy
    cap production deploy
### upload puma.conf
    cap production puma:config
### upload nginx config file
    cap production puma:nginx_config
### create sitemap.xml.gz    
    cap production deploy:sitemap:create
    
### An error occurred while installing pg (0.18.4)  
    sudo apt-get install -y libpq-dev
    
### postgresql Peer authentication failed for user
Need edit file "/etc/postgresql/9.3/main/pg_hba.conf" change line:

    local   all             all                                     peer

to:

    local   all             all                                     md5
## Notes
### rails
    rails g model Aaa --no-test-framework
    rails g controller Aaa index --no-assets --no-helper --no-test-framework

### elasticsearch
    rake -D elasticsearch # display usage instructions
    rake environment elasticsearch:import:model CLASS='Reading::Book' FORCE=y
    
