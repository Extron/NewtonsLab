require 'test_helper'

class SessionsControllerTest < ActionController::TestCase
  test "should get new" do
    get :new
    assert_response :success

    assert_tag "form", :attributes => {:action => "/sessions", :method => "post" }
    assert_tag "input", :attributes => {:id => "email", :type => "text"}
    assert_tag "input", :attributes => {:id => "password", :type => "password"}

  end

end
