require 'test_helper'

class UsersControllerTest < ActionController::TestCase
  def setup
    @user = User.new
    @user.first_name = "Lianne"
    @user.last_name = "Haug"
    @user.email = "Lianne.Haug@Colorado.edu"
    @user.password = "secret"
    @user.password_confirmation = "secret"
    @user.save
  end

  test "should get new" do
    get :new
    assert_response :success
    assert_template "new"

    assert_tag "form", :attributes => {:action => "/users", :method => "post" }
    assert_tag "input", :attributes => {:id => "user_email", :size => "30", :type => "text"}
    assert_tag "input", :attributes => {:id => "user_first_name", :size => "30", :type => "text"}
    assert_tag "input", :attributes => {:id => "user_last_name", :size => "30", :type => "text"}
    assert_tag "input", :attributes => {:id => "user_password", :size => "30", :type => "password"}
    assert_tag "input", :attributes => {:id => "user_password_confirmation", :size => "30", :type => "password"}
    assert_tag "input", :attributes => {:id => "teacher", :type => "checkbox"}
  end

  test "sign_up_success" do
    post :new, :user => {:email => "Lianne.Haug@Colorado.edu",
                        :first_name => "Lianne",
                        :last_name => "Haug",
                        :password => "password",
                        :password_confirmation => "password",
                        :teacher => true}
    #test assignment                    
    user= assigns(:user)
    user.save
    assert_not_nil user

    #test database changes
    new_user = User.find_by_email(@user.email)
    assert_not_nil new_user
  end

end
