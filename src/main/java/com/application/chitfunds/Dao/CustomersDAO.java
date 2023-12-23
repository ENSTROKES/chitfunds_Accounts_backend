/*
 * package com.application.chitfunds.Dao;
 * 
 * import java.util.List; import org.hibernate.Session; import
 * org.hibernate.SessionFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Repository; import
 * org.springframework.transaction.annotation.Transactional; import
 * com.application.chitfunds.entites.Customers;
 * 
 * @Repository
 * 
 * @Transactional public class CustomersDAO {
 * 
 * @Autowired private SessionFactory factory;
 * 
 * @SuppressWarnings("unchecked") public List<Customers> getCustomers() { return
 * getSession().createCriteria(Customers.class).list(); }
 * 
 * private Session getSession() { Session session = factory.getCurrentSession();
 * if (session == null) { session = factory.openSession(); } return session; } }
 */