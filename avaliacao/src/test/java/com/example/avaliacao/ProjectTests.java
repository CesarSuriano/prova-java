package com.example.avaliacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectTests {
	@Autowired
	ProjectRepository projectRepository;
	
	@Test
	public void salvaProjeto() {
		Project project = new Project("Teste");

		Project project2 = projectRepository.save(project);

		

		assertEquals("Teste", project2.getName());
	}

	@Test
	public void adicionaFuncionarioAoProjeto() {

		Project project = new Project("teste");
		project.addEmployee(new Employee("josé", 100.55));
		Project projetoSalvo = projectRepository.save(project);
		
		assertNotNull(projetoSalvo);
		assertEquals("josé", projetoSalvo.getEmployees().iterator().next().getName());
       

	}
	
	@Test
	public void adicionaDoisFuncionariosAoProjeto() {

		Project project1 = new Project("teste1");
		Project project2 = new Project("teste2");
		Employee employee = new Employee("josé", 100.55);
		
		project1.addEmployee(employee);
		project2.addEmployee(employee);

		Project projetoSalvo1 = projectRepository.save(project1);
		Project projetoSalvo2 = projectRepository.save(project2);
		

		assertEquals("josé", projetoSalvo1.getEmployees().iterator().next().getName());
		assertEquals("josé", projetoSalvo2.getEmployees().iterator().next().getName());

       

	}
}
