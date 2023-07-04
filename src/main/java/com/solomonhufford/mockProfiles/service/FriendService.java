package com.solomonhufford.mockProfiles.service;

import java.util.List;

import com.solomonhufford.mockProfiles.entity.Profile;


public interface FriendService {

	List<Profile> viewFriends(String profile_name);

}
