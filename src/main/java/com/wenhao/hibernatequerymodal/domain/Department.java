package com.wenhao.hibernatequerymodal.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2016/09/25.
 */
public class Department {
    private Long id;
    private String name;
    private String provice;
    private String city;
    private String street;
    private Employee manager_id;

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

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Employee getManager_id() {
        return manager_id;
    }

    public void setManager_id(Employee manager_id) {
        this.manager_id = manager_id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provice='" + provice + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
