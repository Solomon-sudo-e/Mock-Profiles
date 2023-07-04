package com.solomonhufford.mockProfiles.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Friend {
	private Long friend_pk;
	private Long profile_fk1;
	private Long profile_fk2;
	
}
