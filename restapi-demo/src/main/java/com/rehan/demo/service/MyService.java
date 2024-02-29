package com.rehan.demo.service;

import java.util.List;

import com.rehan.demo.entites.Product;

public interface MyService {

	List<Product> getProducts();

	Product getProduct(long productId);

	Product addProduct(Product product);

	Product updateProduct(Product product);

	void deleteProduct(long long1);

}
