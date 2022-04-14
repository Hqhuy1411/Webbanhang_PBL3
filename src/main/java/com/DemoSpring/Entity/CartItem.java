package com.DemoSpring.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cartitem")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productEntity;
	@Column
	private int quantily;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return productEntity;
	}
	public void setProduct(Product product) {
		this.productEntity = product;
	}
	public int getQuantily() {
		return quantily;
	}
	public void setQuantily(int quantily) {
		this.quantily = quantily;
	}
	
}
