/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.project.controller;

import com.example.avaliacao.modules.log.controller.LogController;
import com.example.avaliacao.modules.log.model.Log;
import com.example.avaliacao.modules.log.repository.LogRepository;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.repository.ProjectRepository;
import com.example.avaliacao.modules.project.service.ProjectService;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
public class ProjectController {    

    @Autowired
    LogController log;
    
    @Autowired
    ProjectService service;

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        try {
            List<Project> operacao = service.findAll();
            log.createLog(new Log("Busca de projetos", new Date(), "Busca realizada"));
            return  operacao;
        } catch (Exception e) {
            log.createLog(new Log("Erro na busca de projetos", new Date(), e.getMessage()));
            throw e;
        }
    }

    
    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project) {
        try {
        	Project operacao = service.save(project);
        	log.createLog(new Log("Inserção de projetos", new Date(), "Inserção realizada"));
            return operacao;
        } catch (Exception e) {
            log.createLog(new Log("Erro ao inserir projeto", new Date(), e.getMessage()));
            throw e;
        }

    }


    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) {

        try {
        	ResponseEntity<Project> operacao = service.getById(id);
            log.createLog(new Log("Buscando pelo projeto " + id, new Date(), "Busca realizada com sucesso"));
            return operacao;
        } catch (Exception e) {
             log.createLog(new Log("Erro ao buscar pelo projeto " + id, new Date(), e.getMessage()));
             throw e;
        }
    }

    @PutMapping(value = "/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id,
            @RequestBody Project project) {
        try {
        	 ResponseEntity<Project> operacao = service.update(id, project); 
             log.createLog(new Log("Atualizando projeto " + id, new Date(), "Atualização realizada com sucesso"));
             return operacao;
            
        } catch (Exception e) {
            log.createLog(new Log("Erro ao atualizar projeto " + id, new Date(), e.getMessage()));
            throw e;
        }

        
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id) {
        
        try {
        	 ResponseEntity<?> operacao = service.delete(id);
            log.createLog(new Log("Deletando projeto " + id, new Date(), "Projeto deletado com sucesso"));
            return  operacao;
            
        } catch (Exception e) {
            log.createLog(new Log("Erro ao deletar projeto " + id, new Date(), e.getMessage()));
            throw e;
        }
         
    }

}
