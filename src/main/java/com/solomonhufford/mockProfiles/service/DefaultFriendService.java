package com.solomonhufford.mockProfiles.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solomonhufford.mockProfiles.dao.FriendDao;
import com.solomonhufford.mockProfiles.dao.ProfileDao;
import com.solomonhufford.mockProfiles.entity.Friend;
import com.solomonhufford.mockProfiles.entity.Profile;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class DefaultFriendService implements FriendService {

	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private ProfileDao profileDao;
	@Override
	public List<Profile> viewFriends(String profile_name) {
		
		// Converting original profile to a PK so get the list of friends for them.
		Profile friendPk = profileDao.getProfilePK(profile_name);
		//log.debug(friendPk.toString());
		String profileToString = friendPk.toString();
		String profile_pk = profileToString.substring(profileToString.indexOf("profile_pk=") + 11, profileToString.indexOf(", profile_name"));
		
		//Gathers all friends who are linked to that PK
		List<Friend> viewFriendsInColumn1 = friendDao.viewFriendsInColumn1(profile_pk);
		List<Friend> viewFriendsInColumn2 = friendDao.viewFriendsInColumn2(profile_pk);		
	
		/*
		 * Making an array with each "Friend" so that I can modify the strings to specifically get the 
		 * 	"FK" using logic of the way the strings are set up.
		 */
		String[] column1Scan = viewFriendsInColumn1.toString().split("Friend");
		String[] column2Scan = viewFriendsInColumn2.toString().split("Friend");
		
		List<Profile> allFriendsNames = new LinkedList<>();
		
		//Iterating through the arrays and skipping the first array gap which will equal "[" because of the split method greating a single variable.
		for (int i = 1; i < column1Scan.length; i++) {
			String friend = column1Scan[i].substring(column1Scan[i].indexOf("profile_fk2=") + 12, column1Scan[i].indexOf(")"));
			//log.debug(friend);
			List<Profile> column1FriendsAsNames = profileDao.fetchProfileNameFromPk(friend);
			allFriendsNames.addAll(column1FriendsAsNames);
		}
		
		for (int i = 1; i < column2Scan.length; i++) {
			String friend = column2Scan[i].substring(column2Scan[i].indexOf("profile_fk1=") + 12, column2Scan[i].indexOf(", profile_fk2"));
			//log.debug(friend);
			List<Profile> column2FriendsAsNames = profileDao.fetchProfileNameFromPk(friend);
			allFriendsNames.addAll(column2FriendsAsNames);
		}
		
		//log.debug(allFriendsNames.toString());
		return allFriendsNames;
	}

}
