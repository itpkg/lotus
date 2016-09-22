class HomeController < ApplicationController
  def index
    redirect_to Setting.site_home || notices_path
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


  def rate
    item = Object.const_get(params[:type]).find params[:id]
    unless item.nil?
      item.update_column :rate, item.rate+params[:score].to_i
    end
  end

end
