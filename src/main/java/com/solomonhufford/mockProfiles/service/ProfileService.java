package com.solomonhufford.mockProfiles.service;

import java.util.List;

import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.entity.User_Profile;

public interface ProfileService {

	List<Profile> fetchProfile(String profile_name, String password_access);
	
	List<Profile> fetchProfiles();
	
	Profile createProfile(Profile profile);

	Profile updateProfile(Profile profile);

	User_Profile addUserOrUsersToProfile(String profile_name, String user_name);

	void deleteProfile(String profile_name);
	
}
