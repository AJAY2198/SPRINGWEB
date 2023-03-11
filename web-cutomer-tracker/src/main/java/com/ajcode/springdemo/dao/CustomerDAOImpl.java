package com.ajcode.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.ajcode.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	  
	@Override
	public List<Customer> getCustomers() {
		
		//get current hibernate session
    	Session currentSession= sessionFactory.getCurrentSession();
    	
    	//create a query ...sort by lastname
    	Query<Customer> query = currentSession.createQuery("from Customer order by lastName",
    			Customer.class);
    	
    	//execute query and get thresult list
    	
    	List<Customer> customers = query.getResultList();
    	
        return customers;
    	
		
	}


	@Override
	public void saveCustomer(Customer customer) {
		//get current hibernate session
    	Session currentSession= sessionFactory.getCurrentSession();
    	
    	//save or update a customer 
    	currentSession.saveOrUpdate(customer);
    	
    	
    	
		
	}


	@Override
	public Customer getCustomer(int id) {
		//get current hibernate session
    	Session currentSession= sessionFactory.getCurrentSession();
    	
    	//get a custoemr 
    	Customer customer =currentSession.get(Customer.class, id);
    	
		return customer;
	}


	@Override
	public void deleteCustomer(int id) {
		//get current hibernate session
    	Session currentSession= sessionFactory.getCurrentSession();
    	
    	//create delete query 
    	Query<Customer> query = currentSession.createQuery("delete from Customer where id=:customerId");
    	
		query.setParameter("customerId", id);
		
		query.executeUpdate();
	}

}
