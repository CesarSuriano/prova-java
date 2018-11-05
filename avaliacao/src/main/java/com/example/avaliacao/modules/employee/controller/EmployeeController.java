/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.employee.controller;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.employee.repository.EmployeeRepository;
import com.example.avaliacao.modules.log.controller.LogController;
import com.example.avaliacao.modules.log.model.Log;
import com.example.avaliacao.modules.log.repository.LogRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    
//    EntityManager entityManager = new EntityManager();
//    EmployeeTransaction etx = 

    Object operacao;
    
    @Autowired
    LogController log;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        try {
            Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "id");
            operacao = employeeRepository.findAll(sortByCreatedAtDesc);
            log.createLog(new Log("Busca de funcionários", new Date(), "Busca realizada"));
            return (List<Employee>) operacao;
        } catch (Exception e) {
            log.createLog(new Log("Erro ao buscar funcionários", new Date(), e.getMessage()));
        }

        return null;
    }

    @PostMapping("/employees")
    @Transactional
    //@Valid, @RequestBody
    public Employee createEmployee(Employee employee) {
        try {
            operacao = employeeRepository.save(employee);
            log.createLog(new Log("Inserção de funcionários", new Date(), "Inserção realizada com sucesso"));
            return (Employee) operacao;
        } catch (Exception e) {
            log.createLog(new Log("Erro ao inserir funcionário", new Date(), e.getMessage()));
        }
        return null;

    }

    @Transactional
    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> getTodoById(@PathVariable("id") Long id) {

        try {
            operacao = employeeRepository.findById(id)
                    .map(employee -> ResponseEntity.ok().body(employee))
                    .orElse(ResponseEntity.notFound().build());
            log.createLog(new Log("Busca do funcionario " + id, new Date(), "Busca realizada"));
            return (ResponseEntity<Employee>) operacao;
        } catch (Exception e) {
            log.createLog(new Log("Erro ao buscar funcionário " + id, new Date(), e.getMessage()));
        }

        return null;
    }

    @PutMapping(value = "/employees/{id}")
    @Transactional
    public ResponseEntity<Employee> updateTodo(@PathVariable("id") Long id,
            Employee employee) {
        
        try {
             operacao = employeeRepository.findById(id)
                .map(employeeData -> {
                    employeeData.setName(employee.getName());
                    employeeData.setSalary(employee.getSalary());
                    Employee updatedEmployee = employeeRepository.save(employeeData);
                   return ResponseEntity.ok().body(updatedEmployee);
                }).orElse(ResponseEntity.notFound().build());
             
            return (ResponseEntity<Employee>) operacao;
        } catch (Exception e) {
        }
        
        return null;
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
