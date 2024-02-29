package com.rehan.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rehan.demo.entites.Product;
import com.rehan.demo.service.MyService;

//Connect with service --> Connect with dao --> Connect with repository for the DB operation
@RestController
public class MyController {
    
    @Autowired
    private MyService myService;

    @GetMapping("/home")
    public String home() {
        return "Welcome Page";
    }

    @GetMapping("/product")
    public List<Product> getProducts() {
        return this.myService.getProducts();
    }

    @GetMapping("/product/{productId}") // Added forward slash before {productId}
    public Product getProduct(@PathVariable String productId) {
        return this.myService.getProduct(Long.parseLong(productId));
    }

    @PostMapping("/product") // Changed to PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) { // Changed return type
        Product addedProduct = this.myService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED); // Returning ResponseEntity with HttpStatus.CREATED
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product){
        return this.myService.updateProduct(product);
    }

    @DeleteMapping("/product/{productId}") // Added forward slash before {productId}
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String productId) {
        try {
            this.myService.deleteProduct(Long.parseLong(productId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
