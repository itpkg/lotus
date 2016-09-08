require 'elasticsearch/model'

class Notice < ApplicationRecord
  include Elasticsearch::Model
  include Elasticsearch::Model::Callbacks

  validates :content, presence: true
end
