class CreateLeaveWords < ActiveRecord::Migration[5.0]
  def change
    create_table :leave_words do |t|

      t.timestamps
    end
  end
end
