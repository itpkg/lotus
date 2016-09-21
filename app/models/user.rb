class User < ApplicationRecord
  include Gravtastic
  gravtastic

  rolify
  # Include default devise modules. Others available are:
  #
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable,
         :confirmable, :lockable, :timeoutable, :omniauthable

  def send_devise_notification(notification, *args)
    devise_mailer.send(notification, self, *args).deliver_later
  end

  has_many :logs
end
