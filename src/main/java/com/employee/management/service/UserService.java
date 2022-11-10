package com.employee.management.service;

import java.util.List;

import com.employee.management.dto.UserDto;
import com.employee.management.entity.User;

public interface UserService {

	User addUser(UserDto userDto);

	List<User> fetchbyUserName(String userName);

	void createAdmin();

	List<User> fetchAll();

}
