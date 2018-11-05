package com.example.avaliacao.modules.employee.model;

import com.example.avaliacao.modules.project.model.Project;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "EMPLOYEE")
@SequenceGenerator(name = "seq_employee", sequenceName = "seq_employee", initialValue = 1, allocationSize = 1)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee")
	private Long id;

	@Column(nullable = false)
	@Size(min = 2, max = 300)
	private String name;

	private double salary;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "empteste_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "proj_id", referencedColumnName = "id"))
	private Set<Project> projects;

	protected Employee() {
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Funcionário cadastrado";
		// return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id,
		// firstName, lastName);
	}

}
