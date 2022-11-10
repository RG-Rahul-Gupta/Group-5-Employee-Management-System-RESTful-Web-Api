package com.employee.management.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="role")
public class Role  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int role_id;
	
	@Column(name="role_name")
	private String roleName;
	
	@Transient
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

}

