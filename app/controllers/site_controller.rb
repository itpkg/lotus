class SiteController < ApplicationController

  def info
    case request.method_symbol
      when :post
        [:title, :subTitle, :copyright, :keywords, :description].each {|k| Setting.set_site_info k, params[k]}
        Setting.site_author = params[:author]
        flash[:notice] = ' '
      else
        @info= Setting.get_site_info :title
    end
  end

  def seo

  end


end