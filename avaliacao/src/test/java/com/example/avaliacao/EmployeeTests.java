package com.example.avaliacao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.employee.repository.EmployeeRepository;
import com.example.avaliacao.modules.project.exceptions.EmployeeCanNotBeInMoreThanTwoProjectsException;
import com.example.avaliacao.modules.project.model.Project;
import com.example.avaliacao.modules.project.service.ProjectServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ProjectServiceImpl service;

	@Autowired
	private TransactionTemplate txTemplate;

	@Test
	public void salvaFuncionario() {
		Employee employee = new Employee("Cesar", 1000);

		Employee employee2 = employeeRepository.save(employee);

		assertEquals("Cesar", employee2.getName());
	}

	@Test(expected = EmployeeCanNotBeInMoreThanTwoProjectsException.class)
	public void salvaTresProjetosComOMesmoFuncionario() {

		txTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				Employee employee = new Employee("Cesar", 1000);
				employeeRepository.save(employee);
			}
		});

		txTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				Employee employee = employeeRepository.findAll().stream().findFirst().orElse(null);
				Project projeto1 = new Project("Projeto teste1");
				projeto1.addEmployee(employee);
				service.save(projeto1);
			}
		});

		txTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				Employee employee = employeeRepository.findAll().stream().findFirst().orElse(null);
				Project projeto2 = new Project("Projeto teste2");
				projeto2.addEmployee(employee);
				service.save(projeto2);
			}
		});

		txTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				Employee employee = employeeRepository.findAll().stream().findFirst().orElse(null);
				Project projeto3 = new Project("Projeto teste3");
				projeto3.addEmployee(employee);
				service.save(projeto3);
			}
		});
	}
}
