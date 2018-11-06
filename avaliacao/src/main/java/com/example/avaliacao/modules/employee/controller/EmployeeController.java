/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.employee.controller;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.employee.repository.EmployeeRepository;
import com.example.avaliacao.modules.employee.service.EmployeeService;
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
    
    @Autowired
    LogController log;

    @Autowired
    EmployeeService service;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        try {
            List<Employee> operacao = service.findAll();
            log.createLog(new Log("Busca de funcionários", new Date(), "Busca realizada"));
            return operacao;
        } catch (Exception e) {
            log.createLog(new Log("Erro ao buscar funcionários", new Date(), e.getMessage()));
            throw e;
        }

    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        try {
            Employee operacao = service.save(employee);
            log.createLog(new Log("Inserção de funcionários", new Date(), "Inserção realizada com sucesso"));
            return operacao;
        } catch (Exception e) {
            log.createLog(new Log("Erro ao inserir funcionário", new Date(), e.getMessage()));
            throw e;
        }

    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> getTodoById(@PathVariable("id") Long id) {

        try {
        	ResponseEntity<Employee> operacao =service.getById(id); 
            log.createLog(new Log("Busca do funcionario " + id, new Date(), "Busca realizada"));
            return operacao;
        } catch (Exception e) {
            log.createLog(new Log("Erro ao buscar funcionário " + id, new Date(), e.getMessage()));
            throw e;
        }

    }

    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> updateTodo(@PathVariable("id") Long id,
            @RequestBody Employee employee) {
        
        try {
        	ResponseEntity<Employee> operacao = service.update(id, employee); 
        	log.createLog(new Log("Atualização do funcionario " + id, new Date(), "Atualização realizada"));
             
            return operacao;
        } catch (Exception e) {
        	log.createLog(new Log("Erro na atualização do funcionario " + id, new Date(), e.getMessage()));
        	throw e;
        }
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
    	
    	try {
    		ResponseEntity<?> operacao = service.delete(id); 
    		log.createLog(new Log("funcionario " + id + " deletado", new Date(), "Operação realizada"));
    		return operacao;
			
		} catch (Exception e) {
			log.createLog(new Log("Erro ao apagar funcionario " + id, new Date(), e.getMessage()));
        	throw e;
		}    	
         
    }
}
