package com.employee.management.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.employee.management.config.AdminConfig;
import com.employee.management.dto.UserDto;
import com.employee.management.entity.Role;
import com.employee.management.entity.User;
import com.employee.management.exceptions.DataNotFoundException;
import com.employee.management.repository.UserRepository;
import com.employee.management.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final String USER_DATA_NOT_FOUND_ERROR = "User Data not found for %s";

	@Autowired
	private AdminConfig adminConfig;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User addUser(UserDto userDto) {

		User user = User.builder().userName(userDto.getUserName())
				.password(passwordEncoder.encode(userDto.getPassword())).roles(userDto.getRoles()).build();

		return userRepository.saveAndFlush(user);
	}

	@Override
	public List<User> fetchbyUserName(String userName) {
		List<User> users = userRepository.findByUserName(userName);
		if (CollectionUtils.isEmpty(users)) {
			throw new DataNotFoundException(String.format(USER_DATA_NOT_FOUND_ERROR, userName));
		}
		return users;
	}

	@Override
	public void createAdmin() {
		long countAdmin = userRepository.findAdmin().stream().count();
		if (countAdmin == 0) {
			User user = new User();
			Set<Role> roles = new HashSet<Role>();
			roles.add(Role.builder().roleName("ADMIN").build());
			user.setUserName(adminConfig.getUsername());
			user.setPassword(passwordEncoder.encode(adminConfig.getPassword()));
			user.setRoles(roles);
			userRepository.saveAndFlush(user);
		}
	}
	
	@Override
	public List<User> fetchAll() {
		return userRepository.findAll();
	}

}
