package com.solomonhufford.mockProfiles.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solomonhufford.mockProfiles.dao.ProfileDao;
import com.solomonhufford.mockProfiles.dao.UserDao;
import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.entity.User;
import com.solomonhufford.mockProfiles.entity.User_Profile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultuserService implements UserService {

	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProfileDao profileDao;
	

	@Transactional
	@Override
	public User createUser(User userRequest) {
		User userRequestNew = userDao.createUser(userRequest);
		//log.debug("service layer debug userCreate={}", userRequestNew);	
		return userRequestNew;
	}

	@Override
	public User updateUser(User userUpdate) {
		User updatedUser = userDao.updateUser(userUpdate);
		return updatedUser;
	}

	@Override
	public List<User> getUser(String user_name) {
		List<User> getUser = userDao.getUser(user_name);
		return getUser;
	}

	@Override
	public void deleteUser(String user_name) {
		userDao.deleteUser(user_name);
		
	}

	@Override
	public User_Profile addProfileToUser(String profile_name, String user_name) {
		Profile profileForProfileName = profileDao.getProfilePK(profile_name);
		User userForUserName = userDao.getUserId(user_name);
		//log.debug("ProfileForAddingUsers={}", profileForProfileName);
		//log.debug("User for userName request ={}", userForUserName);
		/*
		 * The concept behind this is that a link can be made between users and profiles
		 * to change and edit information of each, however the link needs to be "requested".
		 * 		* I will soon add a "request" function to this just like the friend request.
		 */
		String profileToString = profileForProfileName.toString();
		String userToString = userForUserName.toString();
		
		String profile_pk = profileToString.substring(profileToString.indexOf("profile_pk=") + 11, profileToString.indexOf(", profile_name"));
		String user_id = userToString.substring(userToString.indexOf("user_id=") + 8, userToString.indexOf(", user_name"));
		
		User_Profile addProfileToUsers = userDao.addProfileToUser(user_id, profile_pk);
		
		return addProfileToUsers;
		
	
	}
}
