class ApplicationController < ActionController::Base
  include Pundit
  protect_from_forgery with: :exception

  # locale
  before_action :set_locale

  def set_locale
    I18n.locale = params[:locale] || I18n.default_locale
  end

  def default_url_options
    {locale: I18n.locale}
  end

  def must_admin!
    if current_user.nil? || !current_user.is_admin?
      head :forbidden
    end
  end

end
