package com.aditya.trucker.repository;

import org.springframework.stereotype.Repository;
import com.aditya.trucker.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
