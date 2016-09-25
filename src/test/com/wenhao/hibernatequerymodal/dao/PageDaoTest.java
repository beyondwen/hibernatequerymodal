package com.wenhao.hibernatequerymodal.dao;

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
public class PageDaoTest {
    @Test
    public void page() throws Exception {
        int currentPage = 2;
        int pageSize = 10;
        int firstResult = (currentPage - 1) * pageSize;
        int maxResults = pageSize;
        Session session = HibernateUtils.getSession();
        String hql = "select o from Employee o";
        Query query = session.createQuery(hql);
        query.setFirstResult(firstResult).setMaxResults(maxResults);
        List<Employee> employees = query.list();
        System.out.println(employees);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

}