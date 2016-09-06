# This migration comes from forum (originally 20160906020441)
class CreateForumComments < ActiveRecord::Migration[5.0]
  def change
    create_table :forum_comments do |t|
      t.string :body, null:false
      t.integer :rate, null: false, default: 0

      t.references :user, foreign_key: true, null:false
      t.references :forum_article, foreign_key: true, null:false
      t.timestamps
    end
  end
end
