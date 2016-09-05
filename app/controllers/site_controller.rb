class SiteController < ApplicationController
  before_action :must_admin!

  def info
    case request.method_symbol
      when :post
        [:title, :subTitle, :copyright, :keywords, :description].each { |k| Setting.set_site_info k, params[k] }
        Setting.site_author = params[:author]
        flash[:notice] = ' '
      else
        @info= Setting.get_site_info :title
    end
  end

  def seo
    case request.method_symbol
      when :post
        [:google_site_id, :baidu_site_id].each { |k| Setting[k]= params[k] }
        flash[:notice] = ' '
      else
        @info= Setting.get_site_info :title
    end
  end


end