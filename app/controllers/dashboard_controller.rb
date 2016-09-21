class DashboardController < ApplicationController
  before_action :authenticate_user!
  before_action :must_admin!, only: [:users, :status, :cache]

  def cache
    Rails.cache.clear
    redirect_to dashboard_status_path
  end
end
