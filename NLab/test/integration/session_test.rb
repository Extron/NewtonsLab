require './test/test_helper'

class SessionTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end

  test "the truth" do
  	david = users(:one)
  	assert true
  end

end
