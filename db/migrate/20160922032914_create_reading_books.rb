class CreateReadingBooks < ActiveRecord::Migration[5.0]
  def change
    create_table :reading_books do |t|
      t.string :title, null: false
      t.string :identifier, null: false
      t.string :creator, null: false
      t.string :subject, null: false
      t.string :language, null: false, limit: 5
      t.string :publisher, null: false
      t.string :date, null: false
      t.string :version, null: false

      t.string :uid, null: false, limit:36
      t.string :file, null: false
      t.string :home, null: false
      t.integer :rate, null: false, default: 0

      t.timestamps
    end
    add_index :reading_books, :creator
    add_index :reading_books, :uid, unique: true
    add_index :reading_books, :identifier, unique: true
    add_index :reading_books, :file, unique: true
    add_index :reading_books, :language
    add_index :reading_books, :subject
    add_index :reading_books, :publisher
    add_index :reading_books, :version
  end
end
