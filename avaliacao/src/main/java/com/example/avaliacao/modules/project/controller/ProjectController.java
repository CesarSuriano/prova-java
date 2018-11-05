/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.project.controller;

import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "id");
        return projectRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/projects")
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<Project> getTodoById(@PathVariable("id") Long id) {

        return projectRepository.findById(id)
                .map(project -> ResponseEntity.ok().body(project))
                .orElse(ResponseEntity.notFound().build());
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
