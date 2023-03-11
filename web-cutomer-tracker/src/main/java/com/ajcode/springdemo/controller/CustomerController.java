package com.ajcode.springdemo.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ajcode.springdemo.entity.Customer;
import com.ajcode.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//need injection DAO
	@Autowired
	private CustomerService customerService;
	
	
	
//	@RequestMapping("/list")
//	public String listCustomers(Model theModel) {
//		
//		List<Customer> customers = cutomerDAO.getCustomers();	
//		
//		//add cutomers to model
//		theModel.addAttribute("customers",customers);	
//		
//		return "list-customers";
//	}
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		List<Customer> customers = customerService.getCustomers();	
		
		//add cutomers to model
		theModel.addAttribute("customers",customers);	
		
		return "list-customers";
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor strTrim = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, strTrim);
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer customer = new Customer();
		
		theModel.addAttribute("customer",customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,BindingResult bindingResult) {
		System.out.println("Binding Result: "+ bindingResult);
		if(bindingResult.hasErrors()) {
			return "customer-form";
		}else {
			customerService.saveCustomer(customer);
		}
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForAdd(@RequestParam("customerId") int id, Model theModel) {
		
		//get customer from database
		Customer customer = customerService.getCustomer(id);
		
		
		//set customer to model
		theModel.addAttribute("customer",customer);
		
		//return form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id, Model theModel) {
		//delete customer from database
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	
	
}


