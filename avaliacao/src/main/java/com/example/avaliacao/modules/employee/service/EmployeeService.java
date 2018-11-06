package com.example.avaliacao.modules.employee.service;

import com.example.avaliacao.modules.project.service.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.avaliacao.modules.employee.model.Employee;

public interface EmployeeService {

	public Employee save(Employee employee);
	
	public ResponseEntity<?> delete(Long id);
	
	public ResponseEntity<Employee> update(Long id, Employee employee);
	
	public ResponseEntity<Employee> getById(Long id);
	
	public List<Employee> findAll();
	
	public List<Employee> getAllEmployeesMultipleProject();
	
}
