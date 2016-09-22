class Cms::Comment < ApplicationRecord
  belongs_to :user
  belongs_to :article, class_name: 'Cms::Article'

  validates :user_id, :article_id, :body, presence: true
end
