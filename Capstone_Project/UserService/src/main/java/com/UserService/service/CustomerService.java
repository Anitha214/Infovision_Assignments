package com.UserService.service;

import com.UserService.entity.Customer;
import com.UserService.dto.CustomerDto;
import com.UserService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Optional<Customer> findByCustomerId(Long id){
        return customerRepository.findById(id);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found with id: "+id));
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setPlanId(updatedCustomer.getPlanId());
        return customerRepository.save(customer);
    }

    public CustomerDto getCustomerById(Long id){
        return customerRepository.getCustomerById(id);
    }
}
