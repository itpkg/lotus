Rails.application.routes.draw do

  resource :notices

  get 'dashboard' => 'dashboard#index'
  %w(logs status users).each {|act| get "dashboard/#{act}"}
  delete 'dashboard/cache'

  root 'home#index'

  devise_for :users
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
