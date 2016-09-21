class CreateLeaveWords < ActiveRecord::Migration[5.0]
  def change
    create_table :leave_words do |t|
      t.text :content, null: false
      t.timestamps
    end
  end
end
