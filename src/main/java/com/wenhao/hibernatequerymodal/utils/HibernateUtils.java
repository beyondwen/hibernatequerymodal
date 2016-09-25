package com.wenhao.hibernatequerymodal.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by lenovo on 2016/09/24.
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        StandardServiceRegistry serviceRegistry =new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("读取配置文件失败" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
