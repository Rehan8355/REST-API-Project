package com.rehan.demo.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
	
	@Id
	private long pid;
	private String pname;
	private Double price;
	public long getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(long pid, String pname, Double price) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", price=" + price + "]";
	}
	

}
