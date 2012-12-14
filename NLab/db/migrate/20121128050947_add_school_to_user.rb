class AddSchoolToUser < ActiveRecord::Migration
  def up
  	add_column :users, :school, :string
  end
end
