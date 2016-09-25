package com.wenhao.hibernatequerymodal.dao;

import com.wenhao.hibernatequerymodal.domain.Department;
import com.wenhao.hibernatequerymodal.domain.Employee;
import com.wenhao.hibernatequerymodal.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2016/09/25.
 */
public class EhCacheDaoTest {
    @Test
    public void get() throws Exception {
        Session session = HibernateUtils.getSession();
        Department department = session.get(Department.class, 1L);
        department.getEmployees().size();
        session.close();
        Session session1 = HibernateUtils.getSession();
        Department department1 = session1.get(Department.class, 1L);
        department1.getEmployees().size();
        session1.get(Department.class, 1L);
    }

    @Test
    public void get1() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select o from Employee o";
        Query query = session.createQuery(hql).setCacheable(true);
        List<Employee> employees = query.list();
        for (Employee e : employees) {
            System.out.println(e.getName());
        }

        String hql1 = "select o from Employee o";
        Query query1 = session.createQuery(hql1).setCacheable(false);
        List<Employee> employees1 = query1.list();
        for (Employee e : employees1) {
            System.out.println(e.getName());
        }
    }

}