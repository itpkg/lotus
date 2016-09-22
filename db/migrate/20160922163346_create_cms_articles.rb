class CreateCmsArticles < ActiveRecord::Migration[5.0]
  def change
    create_table :cms_articles do |t|

      t.timestamps
    end
  end
end
