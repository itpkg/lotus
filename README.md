LOTUS - A web application.
---


## System dependencies
### Install rbenv
    sudo apt-get install -y git build-essential make libssl-dev libreadline-dev

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
    sudo apt-get install -y nodejs
       
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
