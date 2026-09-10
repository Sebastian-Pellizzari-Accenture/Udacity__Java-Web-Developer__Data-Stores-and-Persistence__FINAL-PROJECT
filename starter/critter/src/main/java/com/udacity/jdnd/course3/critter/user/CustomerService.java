package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class CustomerService {
    @Autowired 
    CustomerRepository customerRepository;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer getById(Long id){
        return customerRepository.getReferenceById(id);
    }
}
