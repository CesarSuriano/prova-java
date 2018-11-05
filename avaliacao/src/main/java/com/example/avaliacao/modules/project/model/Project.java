package com.example.avaliacao.modules.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.avaliacao.modules.employee.model.Employee;

@Entity
@Table(name = "PROJECT")
@SequenceGenerator(name = "seq_project", sequenceName = "seq_project", initialValue = 1, allocationSize = 1)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project")
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 300)
    private String name;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees;

    protected Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
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

}
