class Reading::Note < ApplicationRecord
  belongs_to :user
  belongs_to :book, class_name: 'Reading::Book'

  validates :user_id, :book_id, :body, presence: true
end
