package com.employee.management.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.employee.management.entity.Role;
import com.employee.management.entity.User;

public class EmployeeUserDetails implements UserDetails {

    private User user; 

	public EmployeeUserDetails(User user) {
//		this.userName = user.getUserName();
//		this.password = user.getPassword();
//		this.authority = (GrantedAuthority) user.getRoles();
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles =  user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role->{
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));	
		}) ;
			
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
