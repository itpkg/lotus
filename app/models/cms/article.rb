class Cms::Article < ApplicationRecord
  belongs_to :user
  has_many :comments, class_name: 'Cms::Comment'
  has_and_belongs_to_many :tags, class_name: 'Cms::Tag'

  validates :user_id, :title, :body, presence: true

  def summary
    body[0..200]
  end
end
