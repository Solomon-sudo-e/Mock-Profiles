package com.solomonhufford.mockProfiles.dao;

import java.util.List;

import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.entity.User_Profile;

public interface ProfileDao {

	 List<Profile> fetchProfiles();
	 
	List<Profile> fetchProfile(String profile, String password_access);
	 
	Profile fetchProfileForCreation(Profile profile);

	Profile updateProfile(Profile profile);

	List<Profile> createProfileResponseWithoutPassword(String profile_name);

	List<Profile> rebuildPrivateProfile(String profile_name);

	List<Profile> createPrivateProfile(String string);

	List<Profile> retrievePublicProfileAfterFilter(String profile_name);

	User_Profile addUsersOrUsersToProfile(String profile_pk, String user_id);

	void deleteProfile(String profile_name);

	Profile getProfilePK(String profile_name);

	List<Profile> fetchProfileNameFromPk(String friend);	

}
