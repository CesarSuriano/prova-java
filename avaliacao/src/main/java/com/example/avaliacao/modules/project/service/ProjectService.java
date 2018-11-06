package com.example.avaliacao.modules.project.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.avaliacao.modules.project.model.Project;

public interface ProjectService {

	public Project save(Project project);
	
	public ResponseEntity<?> delete(Long id);
	
	public ResponseEntity<Project> update(Long id, Project project);
	
	public ResponseEntity<Project> getById(Long id);
	
	public List<Project> findAll();
	
}
