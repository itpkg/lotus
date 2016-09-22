class Cms::Tag < ApplicationRecord
  has_and_belongs_to_many :articles, class_name: 'Cms::Article'

  validates :name, presence: true, uniqueness: true
end
