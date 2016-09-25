package com.wenhao.hibernatequerymodal.dao;

import com.wenhao.hibernatequerymodal.domain.Department;
import com.wenhao.hibernatequerymodal.domain.Employee;
import com.wenhao.hibernatequerymodal.domain.Project;
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

    //1，查询出有员工的部门【distinct】
    @Test
    public void test9() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select distinct o.department_id.name from Employee o";
        Query query = session.createQuery(hql);
        List<String> employees = query.list();
        for (String e : employees) {
            System.out.println(e);
        }
        System.out.println(employees.size());
    }

    //集合在hibernate中经常出现，对集合的操作（size）
    //1，查询出有员工的部门【size】//必须配置双向一对多:部门和员工
    @Test
    public void test10() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select d from Department d where d.employees.size >?";
        Query query = session.createQuery(hql);
        query.setParameter(0, 0);
        List<Department> departments = query.list();
        for (Department e : departments) {
            System.out.println(e);
        }
        System.out.println(departments.size());
    }

    //2，查询出部门信息，按照部门的员工人数排序【使用函数排序】
    @Test
    public void test11() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select d from Department d order by d.employees.size desc ";
        Query query = session.createQuery(hql);
        List<Department> departments = query.list();
        for (Department e : departments) {
            System.out.println(e);
        }
        System.out.println(departments.size());
    }

    //3，查询出没有员工参与的项目【对集合使用size】*/
    @Test
    public void test12() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select d from Project d where d.employees.size=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, 0);
        List<Project> projects = query.list();
        for (Project e : projects) {
            System.out.println(e);
        }
        System.out.println(projects.size());
    }

    //hibernate中的JOIN和LEFT JOIN（使用SQL/HQL对比）
    //1，查询出所有员工及部门名称【JOIN/LEFT JOIN】
    @Test
    public void test13() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select e,d.name from Employee e join e.department_id d";
        Query query = session.createQuery(hql);
        List<Object[]> objects = query.list();
        for (Object[] e : objects) {
            System.out.println(Arrays.toString(e));
        }
        System.out.println(objects.size());
    }

    //2，查询出市场部员工信息及电话
    @Test
    public void test14() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select p,e from Phone p join p.employee_id e join e.department_id d where d.name=?";
        Query query = session.createQuery(hql).setParameter(0, "市场部");
        List<Object[]> objects = query.list();
        for (Object[] e : objects) {
            System.out.println(Arrays.toString(e));
        }
        System.out.println(objects.size());
    }

    //介绍聚集函数/GROUP/HAVING
    //查询出各个部门员工的平均工资和最高工资【使用聚集函数】
    @Test
    public void test15() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select e.name,avg(e.salary),max(e.salary) from Employee e group by e.department_id";
        Query query = session.createQuery(hql);
        List<Object[]> objects = query.list();
        for (Object[] e : objects) {
            System.out.println(Arrays.toString(e));
        }
        System.out.println(objects.size());
    }

    //查询出各个项目参与人数报表
    @Test
    public void test16() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select p.name,count(e) from Project p join p.employees e  group by p.name having count(e) > 3";
        hql = "select p.name,count(e) from Project p join p.employees e  group by p.name";
        Query query = session.createQuery(hql);
        List<Object[]> objects = query.list();
        for (Object[] e : objects) {
            System.out.println(Arrays.toString(e));
        }
        System.out.println(objects.size());
    }

    //查询出大于平均工资的员工信息
    @Test
    public void test17() throws Exception {
        Session session = HibernateUtils.getSession();
        String hql = "select e from Employee e where e.salary >( select avg (o.salary) from Employee o)";
        Query query = session.createQuery(hql);
        List<Employee> objects = query.list();
        for (Employee e : objects) {
            System.out.println(e);
        }
        System.out.println(objects.size());
    }

    //查询出没有留移动电话的员工【使用EXISTS】
    @Test
    public void test18() throws Exception {
        Session session = HibernateUtils.getSession();
        // String hql = "select e.name from Employee e where e.id not in (select p.employee_id from Phone p where p.types='CELL')";
        String hql = "select e.name from Employee e where not exists (select p from e.phones p where p.types='CELL')";
        Query query = session.createQuery(hql);
        List<String> objects = query.list();
        for (String e : objects) {
            System.out.println(e);
        }
        System.out.println(objects.size());
    }


    @Before
    public void create() throws Exception {
        Session session = HibernateUtils.getSession();
        session = HibernateUtils.getSession();
    }

}