/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.employee.controller;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.employee.repository.EmployeeRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cesar
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "id");
        return employeeRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/employees")
    //@Valid, @RequestBody
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping(value="/employees/{id}")
    public ResponseEntity<Employee> getTodoById(@PathVariable("id") Long id) {

        return employeeRepository.findById(id)
                .map(employee -> ResponseEntity.ok().body(employee))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/employees/{id}")
    public ResponseEntity<Employee> updateTodo(@PathVariable("id") Long id,
                                            Employee employee) {
        return employeeRepository.findById(id)
                .map(employeeData -> {
                    employeeData.setName(employee.getName());
                    employeeData.setSalary(employee.getSalary());
                    Employee updatedEmployee = employeeRepository.save(employeeData);
                    return ResponseEntity.ok().body(updatedEmployee);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/employees/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
