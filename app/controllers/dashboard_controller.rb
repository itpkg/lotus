class DashboardController < ApplicationController
  before_action :authenticate_user!
  before_action :must_admin!, only: [:users, :status, :cache]

  def cache
    Rails.cache.clear
    redirect_to dashboard_status_path
  end

  def status
    config   = Rails.configuration.database_configuration
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
end
