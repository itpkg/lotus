# This migration comes from forum (originally 20160906020425)
class CreateForumArticles < ActiveRecord::Migration[5.0]
  def change
    create_table :forum_articles do |t|
      t.string :title, null:false
      t.text :body, null:false
      t.integer :rate, null: false, default: 0
      t.references :user, foreign_key: true, null:false
      t.timestamps
    end
  end
end
