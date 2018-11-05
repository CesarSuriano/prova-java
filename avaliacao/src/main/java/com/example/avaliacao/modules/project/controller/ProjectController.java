/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.project.controller;

import com.example.avaliacao.modules.log.model.Log;
import com.example.avaliacao.modules.log.repository.LogRepository;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.repository.ProjectRepository;
import java.util.Date;
import java.util.List;
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
    LogRepository logRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/projects")
    @Transactional
    public List<Project> getAllProjects() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "id");

        try {
            operacao = projectRepository.findAll(sortByCreatedAtDesc);
            logRepository.save(new Log("Busca de projetos", new Date(), "Busca realizada"));
            return (List<Project>) operacao;
        } catch (Exception e) {
            logRepository.save(new Log("Erro na busca de projetos", new Date(), e.getMessage()));
            return null;
        }

    }

    @Transactional
    @PostMapping("/projects")
    public Project createProject(Project project) {
        try {
            operacao = projectRepository.save(project);
            logRepository.save(new Log("Inserção de projetos", new Date(), "Inserção realizada"));
            return (Project) operacao;
        } catch (Exception e) {
            logRepository.save(new Log("Erro ao inserir projeto", new Date(), e.getMessage()));
            return null;
        }

    }

    @Transactional
    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<Project> getTodoById(@PathVariable("id") Long id) {

        try {
            operacao = projectRepository.findById(id)
                    .map(project -> ResponseEntity.ok().body(project))
                    .orElse(ResponseEntity.notFound().build());
            logRepository.save(new Log("Buscando pelo projeto " + id, new Date(), "Busca realizada com sucesso"));
            return (ResponseEntity<Project>) operacao;
        } catch (Exception e) {
             logRepository.save(new Log("Erro ao buscar pelo projeto " + id, new Date(), e.getMessage()));
             return null;
        }
    }

    @PutMapping(value = "/projects/{id}")
    public ResponseEntity<Project> updateTodo(@PathVariable("id") Long id,
            Project project) {
        return projectRepository.findById(id)
                .map(projectData -> {
                    projectData.setName(project.getName());
                    Project updatedProject = projectRepository.save(projectData);
                    return ResponseEntity.ok().body(updatedProject);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    projectRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
