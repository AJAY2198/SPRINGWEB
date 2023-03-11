package com.ajcode.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajcode.springdemo.dao.CustomerDAO;
import com.ajcode.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	//need injection DAO
	@Autowired
	private CustomerDAO cutomerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return cutomerDAO.getCustomers();	
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		
		cutomerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		
		return cutomerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
	
		cutomerDAO.deleteCustomer(id);	
	}

}
