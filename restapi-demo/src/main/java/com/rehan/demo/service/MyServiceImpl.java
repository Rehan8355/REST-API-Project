package com.rehan.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rehan.demo.dao.ProductDao;
import com.rehan.demo.entites.Product;

@Service
public class MyServiceImpl implements MyService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getProducts() {
		
		return productDao.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product getProduct(long productId) {
		
		return productDao.getOne(productId);
	}

	@Override
	public Product addProduct(Product product) {
		
		return productDao.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		
		return productDao.save(product);
	}

	@Override
	public void deleteProduct(long parseLong) {
		@SuppressWarnings("deprecation")
		Product entity = productDao.getOne(parseLong);
		productDao.delete(entity);
		
	}
	

}
