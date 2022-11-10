package com.employee.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.entity.Employee;
import com.employee.management.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(path = "/list")
	public List<Employee> getAllEmployees() {
		return employeeService.fetchAll();
	}

	@GetMapping(path = "/get/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		return employeeService.fetchbyId(id);
	}

	@GetMapping(path = "/sort")
	public List<Employee> getAllEmployeesSortedbyFirstName(@RequestParam("order") String order) {
		return employeeService.sortByFirstName(order);
	}

	@GetMapping(path = "/search/{firstName}")
	public List<Employee> getAllEmployeesbyFirstName(@PathVariable("firstName") String firstName) {
		return employeeService.fetchAllbyFirstName(firstName);
	}

	@PostMapping(path = "/add")
	public Employee addEmployee(@RequestBody EmployeeDto employeeDto) {
		Employee newEmployee = employeeService.addEmployee(employeeDto);

		return newEmployee;
	}

	@DeleteMapping(path = "/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		String message = employeeService.deleteById(id);
		return message;
	}

	@PutMapping(path = "/{id}")
	public Employee updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable("id") int id) {
		return employeeService.updateEmployee(employeeDto,id);

	}

}
