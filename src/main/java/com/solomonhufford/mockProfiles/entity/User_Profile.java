package com.solomonhufford.mockProfiles.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User_Profile {
	private Long user_id;
	private Long profile_pk;
}
