require 'elasticsearch/rails/tasks/import'

namespace :search do
  desc 'Import data to elasticsearch'
  task import: :environment do
    %w(Notice Forum::Tag Forum::Article Forum::Comment Reading::Book Reading::Note Reading::Page).each do |mod|
      puts "find model #{mod}"
      Object.const_get(mod).import force: true
    end
  end
end