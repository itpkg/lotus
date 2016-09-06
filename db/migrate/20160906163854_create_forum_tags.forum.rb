# This migration comes from forum (originally 20160906020430)
class CreateForumTags < ActiveRecord::Migration[5.0]
  def change
    create_table :forum_tags do |t|
      t.string :name, null:false, unique:true
      t.timestamps
    end

    create_table :forum_articles_tags, id: false do |t|
      t.belongs_to :forum_article, index: true
      t.belongs_to :forum_tag, index: true
    end
  end
end
