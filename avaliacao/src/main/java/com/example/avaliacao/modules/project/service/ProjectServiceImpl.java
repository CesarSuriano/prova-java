package com.example.avaliacao.modules.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.employee.repository.EmployeeRepository;
import com.example.avaliacao.modules.project.exceptions.EmployeeCanNotBeInMoreThanTwoProjectsException;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.repository.ProjectRepository;

@Component
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository repository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Project save(Project project) {

		project.getEmployees().stream().forEach(emp -> {
			if (emp.getId() != null) {
				Optional<Employee> findById = employeeRepository.findById(emp.getId());
				findById.ifPresent(e -> {
					if (e.getProjects() != null
							&& e.getProjects().stream().filter(p -> !p.getId().equals(project.getId())).count() >= 2) {
						throw new EmployeeCanNotBeInMoreThanTwoProjectsException();
					}
				});
			}
		});

		Project newProject = repository.saveAndFlush(project);
		return newProject;
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		return repository.findById(id).map(project -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();

		}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Project> update(Long id, Project project) {
		return repository.findById(id).map(projectData -> {
			projectData.setName(project.getName());
			Project updatedProject = this.save(projectData);
			return ResponseEntity.ok().body(updatedProject);
		}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Project> getById(Long id) {
		return repository.findById(id).map(project -> ResponseEntity.ok().body(project))
				.orElse(ResponseEntity.notFound().build());
	}

	@Override
	public List<Project> findAll() {
		 Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "id");
	     return repository.findAll(sortByCreatedAtDesc);
	}

}
