class HomeController < ApplicationController
  def index

  end

  def about
    @key = :about
    render 'show'
  end

  def help
    @key = :help
    render 'show'
  end

  def faq
    @key = :faq
    render 'show'
  end
end
