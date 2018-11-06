package com.example.avaliacao.modules.employee.service;

import com.example.avaliacao.modules.project.service.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.employee.repository.EmployeeRepository;
import com.example.avaliacao.modules.project.exceptions.EmployeeCanNotBeInMoreThanTwoProjectsException;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.repository.ProjectRepository;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee save(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		return repository.findById(id)
        .map(employee -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Employee> update(Long id, Employee employee) {
		return repository.findById(id).map(employeeData -> {
			employeeData.setName(employee.getName());
			employeeData.setSalary(employee.getSalary());
			Employee updatedEmployee = this.save(employeeData);
			return ResponseEntity.ok().body(updatedEmployee);
		}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Employee> getById(Long id) {
		return repository.findById(id).map(employee -> ResponseEntity.ok().body(employee))
				.orElse(ResponseEntity.notFound().build());
	}

	@Override
	public List<Employee> findAll() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "id");
		return repository.findAll(sortByCreatedAtDesc);
	}

	@Override
	public List<Employee> getAllEmployeesMultipleProject() {
		return null;
	}

}
