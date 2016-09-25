package com.wenhao.hibernatequerymodal.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2016/09/25.
 */
public class Employee {
    private Long id;
    private String name;
    private Department department_id;
    private BigDecimal salary;
    private Date hireDate;

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

    public Department getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Department department_id) {
        this.department_id = department_id;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department_id=" + department_id +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }
}
