package com.example.springpossystem.dao;

import org.example.springpos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<Item,String> {
}
