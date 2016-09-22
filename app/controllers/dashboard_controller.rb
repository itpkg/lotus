class DashboardController < ApplicationController
  before_action :authenticate_user!
  before_action :must_admin!, except: [:index, :logs]

  def cache
    Rails.cache.clear
    redirect_to dashboard_status_path
  end

  def status
    config = Rails.configuration.database_configuration
    case ActiveRecord::Base.connection.adapter_name
      when 'PostgreSQL'
        @database = [
            'select version()',
            "SELECT pg_size_pretty(pg_database_size('#{config[Rails.env]['database']}')) as size",
            'SELECT count(*) as pool FROM pg_stat_activity WHERE NOT pid=pg_backend_pid()'

        ].map do |sql|
          rst = ActiveRecord::Base.connection.execute(sql)
          rst.first
        end
      else
        @database = ''
    end
  end

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
        [:google_verify_id, :baidu_verify_id].each { |k| Setting[k]= params[k] }
        flash[:notice] = ' '
      else

    end
  end

  def nav_bar
    case request.method_symbol
      when :post
        # home = params[:home_goto]
        # unless home.nil?
        #   Setting.set_site_info :home_goto, self.send(home)
        # end

        [:home_goto, :top_links].each { |k| Setting.set_site_info k, params[k] }
        flash[:notice] = ' '
      else

    end
  end

end
