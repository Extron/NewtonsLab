class PuzzleRecord < ActiveRecord::Base
  attr_accessible :puzzle_id, :score, :user_id
  belongs_to :user
  belongs_to :puzzle

  default_scope order('score DESC')
end

