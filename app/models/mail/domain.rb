class Mail::Domain < ApplicationRecord
  has_many :users, class_name:'Mail::User'
  has_many :aliases, class_name: 'Mail::Alias'

  validates :name, presence: true, uniqueness: true
end
