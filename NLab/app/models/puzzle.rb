class Puzzle < ActiveRecord::Base
  attr_accessible :puzzle_type, :score, :user_id, :name

  belongs_to :user
  has_many :puzzle_records

  validates_presence_of :user_id, :on => :create
  validates_presence_of :name
  validates_presence_of :puzzle_type
end
