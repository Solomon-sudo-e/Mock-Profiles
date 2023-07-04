package com.solomonhufford.mockProfiles.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.service.FriendService;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class BasicFriendController implements FriendController {

	@Autowired
	private FriendService friendService; 
	
	@Override
	public List<Profile> viewFriends(String profile_name) {
		log.debug(profile_name);
		return friendService.viewFriends(profile_name);
	}

}
