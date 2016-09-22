class CreateMailAliases < ActiveRecord::Migration[5.0]
  def change
    create_table :mail_aliases do |t|
      t.belongs_to :domain, index: true, null: false
      t.string :source, index: true, null: false
      t.string :destination, index: true, null: false
      t.timestamps
    end
    add_index :mail_aliases, [:source, :destination], unique: true
  end
end
