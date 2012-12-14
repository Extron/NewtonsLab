class DeleteSchoolFromUsers < ActiveRecord::Migration
  def up
  	remove_column :users, :school
  end

  def down
  end
end
