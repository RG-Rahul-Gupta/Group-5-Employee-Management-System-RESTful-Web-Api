package com.employee.management.dto;

import java.util.HashSet;
import java.util.Set;

import com.employee.management.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String userName;
	private String password;
	private Set<Role> roles = new HashSet<>();

}
