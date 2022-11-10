package com.employee.management.serviceImp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.entity.Employee;
import com.employee.management.exceptions.DataNotFoundException;
import com.employee.management.exceptions.OperationNotAllowedException;
import com.employee.management.repository.EmployeeRepository;
import com.employee.management.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private static final String EMPLOYEE_DATA_NOT_FOUND_ERROR = "Employee data not found for %s";
	private static final String DELETE_OPERATION_NOT_ALLOWED_ERROR = "Delete operation not allowed for %s";
	private static final String DELETE_OPERATION_SUCCESS_MSG = "Deleted employee id - %s";

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(EmployeeDto employeeDto) {

		Employee employee = Employee.builder().firstName(employeeDto.getFirstName()).lastName(employeeDto.getLastName())
				.emailId(employeeDto.getEmailId()).build();

		return employeeRepository.saveAndFlush(employee);
	}

	@Override
	public List<Employee> fetchAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee fetchbyId(int id) {
		return employeeRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Employee> fetchAllbyIds(List<Integer> ids) {
		List<Employee> employees = employeeRepository.findAllById(ids);
		if (CollectionUtils.isEmpty(employees)) {
			throw new DataNotFoundException(String.format(EMPLOYEE_DATA_NOT_FOUND_ERROR, ids));
		}
		return employees;
	}

	@Override
	public List<Employee> fetchAllbyFirstName(String firstName) {
		List<Employee> employees = employeeRepository.findByFirstName(firstName);
		if (CollectionUtils.isEmpty(employees)) {
			throw new DataNotFoundException(String.format(EMPLOYEE_DATA_NOT_FOUND_ERROR, firstName));
		}
		return employees;
	}

	@Override
	public List<Employee> sortByFirstName(String order) {
		switch (order) {

		case "ASC":
			return employeeRepository.findAll(Sort.by(Direction.ASC, "firstName"));

		case "DESC":
			return employeeRepository.findAll(Sort.by(Direction.DESC, "firstName"));

		default:
			return employeeRepository.findAll(Sort.by(Direction.ASC, "firstName"));

		}

	}

	@Override
	public String deleteById(int id) {
		try {
			employeeRepository.deleteById(id);
		} catch (Exception argExp) {
			throw new OperationNotAllowedException(String.format(DELETE_OPERATION_NOT_ALLOWED_ERROR, id));
		}
		return String.format(DELETE_OPERATION_SUCCESS_MSG, id);
	}

	@Override
	public Employee updateEmployee(EmployeeDto employeeDto, int id) {
		Employee employeeEntity = employeeRepository.findById(id).orElseThrow();

		if (!StringUtils.equals(employeeEntity.getFirstName(), employeeDto.getFirstName())) {
			employeeEntity.setFirstName(employeeDto.getFirstName());
		}

		if (!StringUtils.equals(employeeEntity.getLastName(), employeeDto.getLastName())) {
			employeeEntity.setLastName(employeeDto.getLastName());
		}

		if (!StringUtils.equals(employeeEntity.getEmailId(), employeeDto.getEmailId())) {
			employeeEntity.setEmailId(employeeDto.getEmailId());
		}

		return employeeRepository.saveAndFlush(employeeEntity);

	}

}
