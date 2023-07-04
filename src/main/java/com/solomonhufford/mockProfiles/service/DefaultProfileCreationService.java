package com.solomonhufford.mockProfiles.service;

import java.util.Collections;
import java.util.LinkedList;
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
public class DefaultProfileCreationService implements ProfileService {
	
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Profile> fetchProfile(String profile_name, String password_access) {
		//log.info("The fetchProfiles method was called with profile_name={}", profile_name);
		
		List<Profile> profile = profileDao.fetchProfile(profile_name, password_access);
		if(profile.isEmpty()) {
			String msg = String.format("No profiles were found with the given profile_name=%s", profile_name);
			
			throw new NoSuchElementException(msg);
		}
		//log.debug("password_access={}", password_access);
		
		//This is made to check if they have a correct password. I will change this to proper encryption soon.
		if(profile.toString().contains("password_access=" + password_access + ",")) {
			List<Profile> checkedProfile = profileDao.createProfileResponseWithoutPassword(profile_name);
			log.debug("Updated profile={}", checkedProfile);
		return checkedProfile;
		} else {
			String msg = String.format("The username or password is invalid. Try again.");
			throw new NoSuchElementException(msg);
		}
	}
	@Transactional
	@Override
	public Profile createProfile(Profile profile) {
		Profile profileCreate = profileDao.fetchProfileForCreation(profile);
		return profileCreate;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Profile> fetchProfiles() {
		//log.info("The fetchProfiles method was called with profile_name={}", profile_name);
		/*
		 * This method has the same concept as the Friends creation. The program 
		 * 	gathers the profiles up then filters out the profiles by splitting the into groups
		 * of an array. Then it goes through a filtration for the keyword "PRIVATE" to 
		 * make sure the UI displays the correct information and only shows what the
		 * profiles have given other users access to see. 
		 */
		List<Profile> profiles = profileDao.fetchProfiles();	
		List<Profile> completeProfileListFiltered = new LinkedList<>();
		
		String profilesAsAString = profiles.toString();
		String[] arrayOfProfiles = profilesAsAString.split("Profile");
		
		for (int i = 1; i < arrayOfProfiles.length; i++) {
			//Should I use .comtains("privacy=PRIVATE")?
			if(arrayOfProfiles[i].contains("privacy=PRIVATE")) {
			List<Profile> privateProfiles = profileDao.createPrivateProfile(arrayOfProfiles[i]);
			completeProfileListFiltered.addAll(privateProfiles);
			//log.debug("PROFILE AFTER ARRAY= {}", privateProfiles);
			} else {
				String profile_name = arrayOfProfiles[i].substring(arrayOfProfiles[i].indexOf("profile_name") + 13, arrayOfProfiles[i].indexOf(", pi"));
				List<Profile> publicProfiles = profileDao.retrievePublicProfileAfterFilter(profile_name);
				completeProfileListFiltered.addAll(publicProfiles);
				//log.debug("Profile public = {}", publicProfiles);
			}
		}
		Collections.sort(completeProfileListFiltered);
		return completeProfileListFiltered;		
	}
	
	
	
	@Override
	public Profile updateProfile(Profile profile) {
		Profile updatedProfile = profileDao.updateProfile(profile);
		return updatedProfile;
	}
	@Override
	public User_Profile addUserOrUsersToProfile(String profile_name, String user_name) {
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
		log.debug(profile_pk);
		String user_id = userToString.substring(userToString.indexOf("user_id=") + 8, userToString.indexOf(", user_name"));
		log.debug(user_id);
		
		User_Profile addUsersOrUsersToProfile = profileDao.addUsersOrUsersToProfile(profile_pk, user_id);
		log.debug(addUsersOrUsersToProfile.toString());
		return addUsersOrUsersToProfile;
		
	}
	@Override
	public void deleteProfile(String profile_name) {
		profileDao.deleteProfile(profile_name);
	}

}
