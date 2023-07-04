package com.solomonhufford.mockProfiles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.solomonhufford.mockProfiles.entity.User;
import com.solomonhufford.mockProfiles.entity.User_Profile;
import com.solomonhufford.mockProfiles.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicUserController implements UserController {

	@Autowired
	UserService userService;
	
	@Override
	public User createUser(User userRequest) {
		//log.debug("userRequest={}", userRequest);
		return userService.createUser(userRequest);
	}


	@Override
	public User updateUser(User userUpdate) {
		User updatedUser = userService.updateUser(userUpdate);
		return updatedUser;
	}

	@Override
	public List<User> getUser(String user_name) {
		List<User> fetchedUser = userService.getUser(user_name);
		return fetchedUser;
	}

	@Override
	public void deleteUser(String user_name) {
		userService.deleteUser(user_name);
	}

	@Override
	public User_Profile addUserOrUsersToProfile(String user_name, String profile_name) {
		return userService.addProfileToUser(profile_name, user_name);
	}

	

}
