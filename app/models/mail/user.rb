class Mail::User < ApplicationRecord
  belongs_to :domain, class_name: 'Mail::Domain'

  validates :domain_id, :password, presence: true

  validates :email, presence: true, uniqueness: true
end
