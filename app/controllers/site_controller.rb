class SiteController < ApplicationController
  before_action :must_admin!

  def info
    case request.method_symbol
      when :post
        [:title, :subTitle, :copyright, :keywords, :description].each { |k| Setting.set_site_info k, params[k] }
        Setting.site_author = params[:author]
        flash[:notice] = ' '
      else

    end
  end

  def seo
    case request.method_symbol
      when :post
        [:google_site_id, :baidu_site_id].each { |k| Setting[k]= params[k] }
        flash[:notice] = ' '
      else

    end
  end

  def page
    case request.method_symbol
      when :post
        Setting.set_page params[:title], params[:body]
        flash[:notice] = ' '
      else
    end
  end


end