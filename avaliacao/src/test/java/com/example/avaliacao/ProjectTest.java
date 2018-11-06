package com.example.avaliacao;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
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
public class ProjectTest {
	
}
