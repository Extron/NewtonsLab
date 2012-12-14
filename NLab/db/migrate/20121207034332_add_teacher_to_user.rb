class AddTeacherToUser < ActiveRecord::Migration
  def up
  	 add_column :users, :teacher, :boolean, :default => false
  end
end
