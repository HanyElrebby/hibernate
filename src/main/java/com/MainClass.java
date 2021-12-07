package com;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Hany Elrebby");
        employee.setEmail("hanielreby01@gmail.com");

        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Employee> students = session.createQuery("from Employee", Employee.class).list();
            students.forEach(s -> System.out.println(s.getName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
