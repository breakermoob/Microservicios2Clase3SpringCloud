package com.microservicios2.clase3.customerService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios2.clase3.customerService.model.Customer;
import com.microservicios2.clase3.customerService.repository.CustomerRepository;
import com.microservicios2.clase3.customerService.service.AccountService;


@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountService accountService;
	
	@PostMapping(value="/customer")
	public Customer save(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	@GetMapping(value="/customer")
	public Iterable<Customer> all(){
		return customerRepository.findAll();
	}
	
	@GetMapping(value="/customer/{customerId}")
	public Customer findByAccountId (@PathVariable Integer customerId){
		Customer customer = customerRepository.findByCustomerId(customerId);
		customer.setAccount(accountService.findByCustomer(customerId));
		return customer;
	}
	
	@PutMapping(value="/customer")
	public Customer update (@RequestBody Customer account) {
		
		return customerRepository.save(account);
	}
	
	@DeleteMapping(value="/customer")
	public void delete(@RequestBody Customer customer) {
		customerRepository.delete(customer);
	}
}