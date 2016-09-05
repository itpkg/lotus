Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html

  scope '(:locale)', locale: /en|zh-CN/ do
    resources :notices
    resources :leave_words, only:[:create, :destroy, :index]

    get 'home', to: 'home#index'
    get 'home/about'
    get 'home/help'
    get 'home/faq'

  end

  devise_for :users

  root to: 'home#index'
end
