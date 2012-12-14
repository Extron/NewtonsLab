class PuzzlesController < ApplicationController
  def new
  	@user = User.find(session[:user].id)
  	@puzzle = @user.puzzles.new
  end

  def create
  	@user = User.find(session[:user].id)
  	@puzzle = @user.puzzles.new(params[:puzzle])

  	#set puzzle type
  	pt = params[:puzzle_type]
  	case pt
  	when "Cannon"
  		@puzzle.puzzle_type = 1
  	when "Car"
  		@puzzle.puzzle_type = 2
  	end


  	if @puzzle.save
      @puzzles = Puzzle.all
  		render "index"
  	else
  		render "new"
  	end
 
  end

  def index
  	@puzzles = Puzzle.all
  end

  def show
    @user = User.find(session[:user].id)
    @puzzle = Puzzle.find(params[:id])
    @record = @user.puzzle_records.find_by_puzzle_id(params[:id])
    puts @puzzle.puzzle_type
      puts" JAAAAA"
    #if this is the first time that the user is playing puzzle
    if @record == nil
      @record = @user.puzzle_records.new
      @record.puzzle_id = params[:id]
      @record.score = 0
      @record.save
    end
  end

  def highscores
    @puzzle = Puzzle.find(params[:puzzle_id])
    @records = @puzzle.puzzle_records.all
  end

  def score
    @puzzle = Puzzle.find(params[:puzzle_id])
    @record = PuzzleRecord.find(params[:record_id])
    @user = User.find(@record.user_id)
    @score = params[:score]

    @record.score = @score.to_i
    @record.save
    render "show"
  end

  def destroy
    @puzzle = Puzzle.find(params[:id])
    @user = User.find(session[:user].id)
    @record = @user.puzzle_records.find_by_puzzle_id(@puzzle.id)

    @puzzle.destroy

    if @record != nil
      @record.destroy
    end

    @puzzles = Puzzle.all
    render "index"
  end

end
