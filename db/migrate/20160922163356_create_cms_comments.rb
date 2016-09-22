class CreateCmsComments < ActiveRecord::Migration[5.0]
  def change
    create_table :cms_comments do |t|
      t.text :body, null: false
      t.integer :rate, null: false, default: 0

      t.references :user, foreign_key: true, null: false
      t.belongs_to :article, index: true, null: false

      t.timestamps
    end
  end
end
