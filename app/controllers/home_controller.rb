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

  def google
    code = Setting.google_verify_id
    if params[:id] == code
      render plain: "google-site-verification: google#{code}.html"
    else
      head :not_found
    end
  end

  def baidu
    code = Setting.baidu_verify_id
    if params[:id] == code
      render plain: code
    else
      head :not_found
    end
  end
end