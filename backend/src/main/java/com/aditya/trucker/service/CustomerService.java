package com.aditya.trucker.service;

import com.aditya.trucker.model.Customer;
import com.aditya.trucker.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepo.findById(id).map(customer -> {
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setNumber(updatedCustomer.getNumber());
            customer.setReviews(updatedCustomer.getReviews());
            return customerRepo.save(customer);
        }).orElseGet(() -> {
            updatedCustomer.setId(id);
            return customerRepo.save(updatedCustomer);
        });
    }
}
