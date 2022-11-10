package com.employee.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.dto.UserDto;
import com.employee.management.entity.User;
import com.employee.management.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/list")
	public List<User> fetchAllUsers() {
		 return userService.fetchAll();
		 
	}
	
	@PostMapping(path = "/add")
	public User addEmployee(@RequestBody UserDto userDto) {
		User newUser = userService.addUser(userDto);
		return newUser;
	}

}
