package com.solomonhufford.mockProfiles.dao;

import java.util.List;

import com.solomonhufford.mockProfiles.entity.Friend;

public interface FriendDao {

	List<Friend> viewFriendsInColumn1(String profile_pk);

	List<Friend> viewFriendsInColumn2(String profile_pk);
}
