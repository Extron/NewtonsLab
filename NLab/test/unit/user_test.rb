require 'test_helper'

class UserTest < ActiveSupport::TestCase
   test "should not save without attributes" do
     user = User.new
     assert !user.save
   end

   test "password encryption"do
   	user = users(:Lianne)
      user.password = "secret"
      user.password_confirmation = "secret"

   	user.save

   	assert user.password_hash != user.password
   	assert user.password_salt != user.password
   end

   test "uniquness of email" do
   	user = User.new
   	user.first_name = "Lianne"
   	user.last_name = "Haug"
   	user.email = "lianne.haug@colorado.edu"
   	user.password = "secret"
   	user.save

   	user2 = User.new
   	user2.first_name = "Lianne"
   	user2.last_name = "Haug"
   	user2.email = "lianne.haug@colorado.edu"
   	user2.password = "secret"
   	user2.password_confirmation = "secret"

   	assert !user2.save
   end

   test "teacher is false by default" do
   	user = User.new
   	user.first_name = "Lianne"
   	user.last_name = "Haug"
   	user.email = "lianne.haug@colorado.edu"
   	user.password = "secret"
   	user.password_confirmation = "secret"
   	user.save

   	assert !user.teacher
   end

end
