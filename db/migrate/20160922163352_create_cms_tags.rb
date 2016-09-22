class CreateCmsTags < ActiveRecord::Migration[5.0]
  def change
    create_table :cms_tags do |t|
      t.string :name, null: false
      t.integer :rate, null: false, default: 0
      t.timestamps
    end

    add_index :cms_tags, :name, unique: true

    create_table :cms_articles_tags, id: false do |t|
      t.belongs_to :article, index: true
      t.belongs_to :tag, index: true
    end

    add_index :cms_articles_tags, [:article_id, :tag_id], unique: true
  end
end
