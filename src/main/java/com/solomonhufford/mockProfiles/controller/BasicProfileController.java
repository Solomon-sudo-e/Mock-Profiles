package com.solomonhufford.mockProfiles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.entity.User_Profile;
import com.solomonhufford.mockProfiles.service.ProfileService;

@RestController

public class BasicProfileController implements ProfileController {

	@Autowired
	private ProfileService profileService;
	
	
	@Override
	public List<Profile> fetchProfile(String profile_name, String password_access) {
		//log.info("profile_name={}, password ={}", profile_name, password);
		return profileService.fetchProfile(profile_name, password_access);
	}


	@Override
	public Profile createProfile(Profile profile) {
		return profileService.createProfile(profile);
	}


	@Override
	public List<Profile> fetchProfiles() {
		// TODO Auto-generated method stub
		return profileService.fetchProfiles();
	}


	@Override
	public Profile updateProfile(Profile profile) {
		return profileService.updateProfile(profile);
	}


	@Override
	public User_Profile addUserOrUsersToProfile(String profile_name, String user_name) {
		return profileService.addUserOrUsersToProfile(profile_name, user_name);
	}


	@Override
	public void deleteProfile(String profile_name) {
		profileService.deleteProfile(profile_name);
	}


}
