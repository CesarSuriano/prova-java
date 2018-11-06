package com.example.avaliacao.modules.employee.model;

import com.example.avaliacao.modules.project.model.Project;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
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
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee")
	private Long id;

	@Column(nullable = false)
	@Size(min = 2, max = 300)
	private String name;

	private double salary;

	@ManyToMany(mappedBy = "employees")
	@JsonBackReference
	private Set<Project> projects;

	public Employee() {
	}

	public Employee(Long id, @Size(min = 2, max = 300) String name, double salary, Set<Project> projects) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.projects = projects;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
