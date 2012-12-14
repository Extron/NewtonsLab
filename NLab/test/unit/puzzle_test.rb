require 'test_helper'

class PuzzleTest < ActiveSupport::TestCase
  test "should not save without attributes" do
  	puzzle = Puzzle.new
  	assert !puzzle.save
  end

  test "should have correct relations" do
  	user = users(:Lianne)
  	user.password = "secret"
  	user.save

  	puzzle = user.puzzles.new
  	puzzle.name = "test"
  	puzzle.puzzle_type = 1

  	puzzle.save

  	assert puzzle.user_id == user.id
  end

  test "should be found by user" do
    user = users(:Lianne)
    user.password = "secret"
    user.save

    puzzle = user.puzzles.new
    puzzle.name = "test"
    puzzle.puzzle_type = 1
    puzzle.save

    assert_equal user.puzzles.first, puzzle
  end

  test "user should be found by puzzle" do
    user = users(:Lianne)
    user.password = "secret"
    user.save

    puzzle = user.puzzles.new
    puzzle.name = "test"
    puzzle.puzzle_type = 1
    puzzle.save

    assert_equal user, User.find(puzzle.user_id)
  end

end
