package com.wenhao.hibernatequerymodal.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2016/09/25.
 */
public class Phone {
    private Long id;
    private String types;
    private String number;
    private Employee employee_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Employee employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", types='" + types + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
