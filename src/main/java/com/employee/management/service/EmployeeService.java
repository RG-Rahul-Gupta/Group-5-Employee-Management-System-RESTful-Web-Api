package com.employee.management.service;

import java.util.List;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.entity.Employee;

public interface EmployeeService {

	List<Employee> fetchAll();

	Employee fetchbyId(int id);

	List<Employee> fetchAllbyIds(List<Integer> ids);

	List<Employee> fetchAllbyFirstName(String firstName);

	List<Employee> sortByFirstName(String order);

	String deleteById(int id);

	Employee updateEmployee(EmployeeDto employeeDto, int id);

	Employee addEmployee(EmployeeDto employeeDto);

}
