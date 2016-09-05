Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html

  scope '(:locale)' do
    resources :notices, except: :show
    resources :leave_words, only:[:create, :destroy, :index]

    get 'dashboard' => 'dashboard#index'
    get 'dashboard/logs'

    %w(info seo page).each do |act|
      get "site/#{act}"
      post "site/#{act}"
    end


    get 'home', to: 'home#index'
    get 'home/about'
    get 'home/help'
    get 'home/faq'

  end

  devise_for :users

  get 'google(*id).html', to: 'home#google'
  get 'baidu_verify_(*id).html', to: 'home#baidu'

  root to: 'home#index'
end
