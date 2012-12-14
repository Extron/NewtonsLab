class AddPuzzleName < ActiveRecord::Migration
  def up
  	add_column :puzzles, :name, :string
  end

  def down
  end
end
