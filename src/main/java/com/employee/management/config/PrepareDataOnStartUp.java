package com.employee.management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.employee.management.service.UserService;

@Configuration
public class PrepareDataOnStartUp {
	
	@Autowired
	private UserService userService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertDefaultData(ApplicationReadyEvent event) {
		userService.createAdmin();
	}

}
