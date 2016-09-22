Rails.application.routes.draw do

  # ------------------

  namespace :mail do
    root 'home#index'

    get 'change_password' => 'home#change_password'
    post 'change_password' => 'home#change_password'

    %w(postfix dovecot).each {|act| get act => "home##{act}"}
    resources :domains, expect: [:show, :index]
    resources :users, expect: [:show, :index]
    resources :aliases, expect: [:show, :index]
  end

  # ------------------

  namespace :cms do
    resources :articles
    resources :tags
    resources :comments, except: :show
  end

  # ------------------

  namespace :reading do
    resources :books, only: [:index, :destroy, :show]
    get 'page/:id/*file' => 'page#index', as: :page

    resources :notes, except: [:show]
  end

  # ------------------

  get 'dict' => 'dict#index'
  post 'dict' => 'dict#index'

  # ------------------

  resources :leave_words, except: [:show, :edit, :update]

  resources :notices, except: :show

  get 'dashboard' => 'dashboard#index'
  %w(logs status users).each { |act| get "dashboard/#{act}" }
  %w(info seo nav_bar).each do |act|
    get "dashboard/#{act}"
    post "dashboard/#{act}"
  end
  delete 'dashboard/cache'

  post 'rate' => 'home#rate'

  get 'google(*id).html', to: 'home#google'
  get 'baidu_verify_(*id).html', to: 'home#baidu'

  # ------------------

  root 'home#index'

  devise_for :users

  require 'sidekiq/web'
  authenticate :user, lambda { |u| u.is_admin? } do
    mount Sidekiq::Web => '/jobs'
  end
# For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
