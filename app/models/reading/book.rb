class Reading::Book < ApplicationRecord
  has_many :notes, class_name: 'Reading::Note'

  validates :creator, :language, :subject, :publisher, presence: true

  validates :identifier, presence: true, uniqueness: true

end
