package com.wenhao.hibernatequerymodal.domain;

import javax.enterprise.inject.New;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2016/09/25.
 */
public class Project {

    private Long id;
    private String name;
    private Employee manager;
    private Set<Employee> employees = new HashSet<Employee>();

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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
