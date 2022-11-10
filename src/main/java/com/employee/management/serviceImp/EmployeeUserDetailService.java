package com.employee.management.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employee.management.entity.User;
import com.employee.management.security.EmployeeUserDetails;
import com.employee.management.service.UserService;

@Service
public class EmployeeUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.fetchbyUserName(username).stream().findFirst().orElseThrow(()->new UsernameNotFoundException("Following User Name does Not Exist in our Database"));
		System.out.println("User Found");
		return new EmployeeUserDetails(user) ;
	}
}
