package com.solomonhufford.mockProfiles.controller.support;

public class CreateAccountTestSupport extends BaseTest {
	protected String createUserBody() {
		//@formatter:off
		return "{\n"
			+ "  \"date_of_birth\":\"02252002\",\n"
			+ "  \"age\":\"21\",\n"
			+ "  \"gender\":\"MALE\",\n"
			+ "  \"email\":\"solo.hufford@gmail.com\",\n"
			+ "  \"location\":\"Siloam Springs, Arkansas\"\n"
			+ "}";
		
		//@formatter:on
	}
	protected String createProfileBody() {
		//@formatter:off
		return "{\n"
			+ "  \"profile_name\":\"Solomon_Hufford\",\n"
			+ "  \"biography\":\"Creator of the project, and first test!\",\n"
			+ "  \"status\":\"ISONLINE\"\n"
			+ "}";
		
		//@formatter:on
	}
}
