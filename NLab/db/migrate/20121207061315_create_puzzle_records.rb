class CreatePuzzleRecords < ActiveRecord::Migration
  def change
    create_table :puzzle_records do |t|
      t.integer :user_id
      t.integer :puzzle_id
      t.integer :score

      t.timestamps
    end
  end
end
