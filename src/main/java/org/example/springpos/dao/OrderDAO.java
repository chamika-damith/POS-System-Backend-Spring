package org.example.springpos.dao;

import org.example.springpos.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,String> {

}
