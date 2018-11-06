package com.example.avaliacao;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.employee.repository.EmployeeRepository;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AvaliacaoApplicationTests {

	@Resource
	ProjectRepository projectRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void salvaFuncionario() {
		Employee employee = new Employee("Cesar", 1000);

		employeeRepository.save(employee);

		Employee employee2 = employeeRepository.getOne(Long.valueOf(1));

		assertEquals("Cesar", employee2.getName());
	}

	@Test
	public void salvaProjeto() {
		Project project = new Project("Teste");

		projectRepository.save(project);

		Project project2 = projectRepository.getOne(Long.valueOf(1));

		assertEquals("Cesar", project2.getName());
	}

	@Test
	public void adicionaFuncionarioAoProjeto() {

//		Project project = new Project("teste");
//		
//		
//		Set<Employee> employee = (Set<Employee>) new Employee("Cesar", 1000);
//		project.setEmployees(employee);
//		
//		projectRepository.save(project);
//		
//		Project project2 = projectRepository.getOne(Long.valueOf(1));
//		
//
//		
//        
//        while (project2.getEmployees().iterator().hasNext()) {
//        	Employee employee2 = (Employee) project2.getEmployees().iterator().next();
//        	assertEquals("Cesar", employee2.getName()); 
//        }

	}

}
