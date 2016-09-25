package com.wenhao.hibernatequerymodal.dao;

import com.wenhao.hibernatequerymodal.domain.Employee;
import com.wenhao.hibernatequerymodal.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2016/09/25.
 */
public class DomainDaoTest {
    //1，查询所有员工【查询实体类型】
    @Test
    public void test1() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select o from Employee o";
        Query query = session.createQuery(hql);
        List<Employee> employees = query.list();
        System.out.println("=======================");
        System.out.println(employees);
        session.close();
        session.clear();
        System.out.println("************************");
        System.out.println(employees.get(2).getDepartment_id().getName());
    }

    //2，查询所有员工的姓名和所属部门名称【查询特定属性】
    @Test
    public void test2() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select o.name,o.department_id.name from Employee o";
        Query query = session.createQuery(hql);
        List<Object[]> employees = query.list();
        for (Object[] e : employees) {
            System.out.println(Arrays.toString(e));
        }
    }

    //3，查询出所有在成都和广州工作的员工【查询结果过滤】
    @Test
    public void test3() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select o from Employee o where o.department_id.city=? or o.department_id.city=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, "成都").setParameter(1, "广州");
        List<Employee> employees = query.list();
        for (Employee e : employees) {
            System.out.println(e.getName());
        }
    }

    //4，查询出所有员工信息，按照月薪排序【查询排序】
    @Test
    public void test4() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select o from Employee o order by o.salary";
        Query query = session.createQuery(hql);
        List<Employee> employees = query.list();
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    //5，查询出所有员工信息，按照部门编号排序【使用关联对象属性排序】
    @Test
    public void test5() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select o from Employee o order by o.department_id.id";
        Query query = session.createQuery(hql);
        List<Employee> employees = query.list();
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    //6，查询出在恩宁路和八宝街上班的员工信息【使用IN】
    @Test
    public void test6() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select o from Employee o where o.department_id.street in (?,?)";
        Query query = session.createQuery(hql);
        query.setParameter(0, "八宝街").setParameter(1, "恩宁路");
        List<Employee> employees = query.list();
        for (Employee e : employees) {
            System.out.println(e);
        }
        System.out.println(employees.size());
    }

    //7，查询出工资在5000-6000的员工【使用BETWEEN..AND..】
    @Test
    public void test7() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select o from Employee o where o.salary between ? and ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, new BigDecimal(5000)).setParameter(1, new BigDecimal(6000));
        List<Employee> employees = query.list();
        for (Employee e : employees) {
            System.out.println(e);
        }
        System.out.println(employees.size());
    }

    //8，查询出姓名包含er或者en的员工【使用LIKE】
    @Test
    public void test8() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select o from Employee o where o.name like ? or o.name like ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, "%er%").setParameter(1, "%en%");
        List<Employee> employees = query.list();
        for (Employee e : employees) {
            System.out.println(e);
        }
        System.out.println(employees.size());
    }

    @Before
    public void create() throws Exception {
        Session session = HibernateUtils.getSession();
        session = HibernateUtils.getSession();
    }
}