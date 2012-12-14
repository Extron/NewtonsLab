class CreatePuzzles < ActiveRecord::Migration
  def change
    create_table :puzzles do |t|
      t.integer :puzzle_type
      t.integer :score
      t.integer :user_id

      t.timestamps
    end
  end
end
