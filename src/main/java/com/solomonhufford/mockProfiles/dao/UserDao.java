package com.solomonhufford.mockProfiles.dao;

import java.util.List;

import com.solomonhufford.mockProfiles.entity.Gender;
import com.solomonhufford.mockProfiles.entity.User;
import com.solomonhufford.mockProfiles.entity.User_Profile;

public interface UserDao {

	User createUser(User userRequest);

	List<User> fetchUser(String user_name);

	User getUserId(String user_name);

	User updateUser(User userUpdate);

	List<User> getUser(String user_name);

	void deleteUser(String user_name);

	User_Profile addProfileToUser(String user_id, String profile_pk);

}
