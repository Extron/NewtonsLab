class UsersController < ApplicationController
  def new
  	@user = User.new
  end

def create
  @user = User.new(params[:user])

  #correct checkbox settings
  @user.teacher = params[:teacher]
  if @user.teacher == nil
    @user.teacher = false
  end

  if @user.save
  	session[:user] = @user
    redirect_to root_url, :notice => "Signed up!"
  else
    render "new"
  end
end

def edit
  render "update"
end

def update
  @user = User.find(session[:user].id)
  @user.update_attributes(params[:user])
  @records = @user.puzzle_records.all
  render "show"


end

  def show
    @user = User.find(session[:user].id)
    @records = @user.puzzle_records.all
  end

  def index
  end

end
