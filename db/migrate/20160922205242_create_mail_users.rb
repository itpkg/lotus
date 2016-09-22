class CreateMailUsers < ActiveRecord::Migration[5.0]
  def change
    create_table :mail_users do |t|
      t.belongs_to :domain, index: true, null: false
      t.string :password, null: false
      t.string :email, null: false
      t.timestamps
    end
    add_index :mail_users, :email, unique: true
  end
end
