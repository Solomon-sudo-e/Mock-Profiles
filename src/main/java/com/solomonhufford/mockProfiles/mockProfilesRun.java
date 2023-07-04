package com.solomonhufford.mockProfiles;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.solomonhufford.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class mockProfilesRun {

	public static void main(String[] args) {
		SpringApplication.run(mockProfilesRun.class, args);
	}

}
