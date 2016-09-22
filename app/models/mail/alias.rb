class Mail::Alias < ApplicationRecord
  belongs_to :domain, class_name: 'Mail::Domain'

  validates :domain_id, :source, :destination, presence: true
end
