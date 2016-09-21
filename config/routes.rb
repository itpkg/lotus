Rails.application.routes.draw do

  resources :leave_words, except: [:show, :edit, :update]

  resources :notices, except: :show

  get 'dashboard' => 'dashboard#index'
  %w(logs status users).each { |act| get "dashboard/#{act}" }
  delete 'dashboard/cache'

  root 'home#index'

  devise_for :users

  require 'sidekiq/web'
  authenticate :user, lambda { |u| u.is_admin? } do
    mount Sidekiq::Web => '/jobs'
  end
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
