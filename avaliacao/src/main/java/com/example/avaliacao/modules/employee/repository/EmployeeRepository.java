/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.employee.repository;

import com.example.avaliacao.modules.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cesar
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
	
}
