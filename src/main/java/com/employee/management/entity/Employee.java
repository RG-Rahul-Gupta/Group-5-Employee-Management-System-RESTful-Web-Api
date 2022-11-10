package com.employee.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="employee")
public class Employee  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emp_id;
	
	private String firstName;
	private String lastName;
	private String emailId;
	
//	@JsonManagedReference
//	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinColumn(name="user_id",nullable = false,referencedColumnName = "user_id" )
//	private User user;
	
}
