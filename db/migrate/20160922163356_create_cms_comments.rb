class CreateCmsComments < ActiveRecord::Migration[5.0]
  def change
    create_table :cms_comments do |t|

      t.timestamps
    end
  end
end
