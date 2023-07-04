package com.solomonhufford.mockProfiles.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private long user_id;
	private String user_name;
	private String friend_fk;
	
	@Pattern(regexp = "[0-9]*")
	@Length(min = 8, max = 8)
	private String date_of_birth;
	private String email;
	
	private int age;
	private Gender gender;
	private String location;
	
	
	
}
