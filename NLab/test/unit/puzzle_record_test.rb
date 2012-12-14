require 'test_helper'

class PuzzleRecordTest < ActiveSupport::TestCase
  test "should have correct relationships" do
  	user = users(:Lianne)
  	user.password = "secret"
  	user.save

  	puzzle = user.puzzles.new
  	puzzle.name = "test"
  	puzzle.puzzle_type = 1
  	puzzle.save

  	record = user.puzzle_records.new
  	record.puzzle_id = user.puzzles.first
    record.save

  	assert puzzle.save
  end

  test "should be able to be found by user" do
    user = users(:Lianne)
    user.password = "secret"
    user.save

    puzzle = user.puzzles.new
    puzzle.name = "test"
    puzzle.puzzle_type = 1
    puzzle.save

    record = user.puzzle_records.new
    record.puzzle_id = user.puzzles.first
    record.save

    assert_equal user.puzzle_records.first, record
  end

  test "should be able to find puzzle" do
    user = users(:Lianne)
    user.password = "secret"
    user.save

    puzzle = user.puzzles.new
    puzzle.name = "test"
    puzzle.puzzle_type = 1
    puzzle.save

    record = user.puzzle_records.new
    record.puzzle_id = user.puzzles.first.id
    record.save

    assert_equal puzzle, Puzzle.find(record.puzzle_id)
  end

  test "should be able to find user" do
    user = users(:Lianne)
    user.password = "secret"
    user.save

    puzzle = user.puzzles.new
    puzzle.name = "test"
    puzzle.puzzle_type = 1
    puzzle.save

    record = user.puzzle_records.new
    record.puzzle_id = user.puzzles.first.id
    record.save

    assert_equal user, User.find(record.user_id)
  end

end
