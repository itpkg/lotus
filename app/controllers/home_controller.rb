class HomeController < ApplicationController
  def index
    redirect_to Setting.site_home || notices_path
  end
end
