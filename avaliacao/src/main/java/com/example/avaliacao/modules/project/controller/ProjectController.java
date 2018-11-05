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

    Object operacao;
    
    @Autowired
    LogController log;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/projects")
    @Transactional(propagation = Propagation.REQUIRED, 
                   isolation = Isolation.DEFAULT, 
                   readOnly = false)
    public List<Project> getAllProjects() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "id");

        try {
            operacao = projectRepository.findAll(sortByCreatedAtDesc);
            log.createLog(new Log("Busca de projetos", new Date(), "Busca realizada"));
            return (List<Project>) operacao;
        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            TransactionAspectSupport.currentTransactionStatus().flush();
            log.createLog(new Log("Erro na busca de projetos", new Date(), e.getMessage()));
            return null;
        }

    }

    
    @PostMapping("/projects")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Project createProject(@RequestBody Project project) {
        try {
        	Project operacao = projectRepository.save(project);
            log.createLog(new Log("Inserção de projetos", new Date(), "Inserção realizada"));
            return operacao;
        } catch (Exception e) {
            log.createLog(new Log("Erro ao inserir projeto", new Date(), e.getMessage()));
            throw e;
        }

    }

    @Transactional
    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<Project> getTodoById(@PathVariable("id") Long id) {

        try {
            operacao = projectRepository.findById(id)
                    .map(project -> ResponseEntity.ok().body(project))
                    .orElse(ResponseEntity.notFound().build());
            log.createLog(new Log("Buscando pelo projeto " + id, new Date(), "Busca realizada com sucesso"));
            return (ResponseEntity<Project>) operacao;
        } catch (Exception e) {
             log.createLog(new Log("Erro ao buscar pelo projeto " + id, new Date(), e.getMessage()));
             return null;
        }
    }

    @Transactional
    @PutMapping(value = "/projects/{id}")
    public ResponseEntity<Project> updateTodo(@PathVariable("id") Long id,
            Project project) {
        try {
            operacao = projectRepository.findById(id)
                .map(projectData -> {
                    projectData.setName(project.getName());
                    Project updatedProject = projectRepository.save(projectData);
                    return  ResponseEntity.ok().body(updatedProject);
                }).orElse(ResponseEntity.notFound().build());
             log.createLog(new Log("Atualizando projeto " + id, new Date(), "Atualização realizada com sucesso"));
             return (ResponseEntity<Project>) operacao;
            
        } catch (Exception e) {
            log.createLog(new Log("Erro ao atualizar projeto " + id, new Date(), e.getMessage()));
        }
        
        return null;
        
    }

    @Transactional
    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
        
        try {
            projectRepository.findById(id)
                .map(project -> {
                    projectRepository.deleteById(id);
                    operacao = ResponseEntity.ok().build();
                    log.createLog(new Log("Deletando projeto " + id, new Date(), "Projeto deletado com sucesso"));
                    return operacao;
                }).orElse(ResponseEntity.notFound().build());
            
        } catch (Exception e) {
            log.createLog(new Log("Erro ao deletar projeto " + id, new Date(), e.getMessage()));
        }
        
        return null;
         
    }

}
