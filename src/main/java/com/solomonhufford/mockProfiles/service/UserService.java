package com.solomonhufford.mockProfiles.service;

import java.util.List;

import com.solomonhufford.mockProfiles.entity.User;
import com.solomonhufford.mockProfiles.entity.User_Profile;

import lombok.extern.slf4j.Slf4j;


public interface UserService {

	User createUser(User user);

	User updateUser(User userUpdate);

	List<User> getUser(String user_name);

	void deleteUser(String user_name);

	User_Profile addProfileToUser(String profile_name, String user_name);

}
