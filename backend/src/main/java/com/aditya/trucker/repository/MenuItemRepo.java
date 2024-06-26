package com.aditya.trucker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aditya.trucker.model.MenuItem;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Long>{

}
