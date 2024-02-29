package com.rehan.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rehan.demo.entites.Product;


public interface ProductDao extends JpaRepository<Product, Long> {

}
