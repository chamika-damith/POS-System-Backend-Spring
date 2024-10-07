package com.example.springpossystem.dao;

import org.example.springpos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer,String> {
}
