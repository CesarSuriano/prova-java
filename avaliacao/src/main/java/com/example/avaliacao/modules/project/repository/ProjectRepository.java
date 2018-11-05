/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.project.repository;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cesar
 */
public interface ProjectRepository  extends JpaRepository<Project, Long>{
    
}
