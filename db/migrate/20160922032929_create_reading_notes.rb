class CreateReadingNotes < ActiveRecord::Migration[5.0]
  def change
    create_table :reading_notes do |t|
      t.text :body, null: false
      t.references :user, foreign_key: true, null: false
      t.belongs_to :book, index: true, null: false

      t.integer :rate, null: false, default: 0
      t.timestamps
    end
  end
end
