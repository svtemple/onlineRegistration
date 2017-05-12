package com.dronamraju.svtemple.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserProductId implements java.io.Serializable {

	private User user;
    private Product product;

	@Override
	public String toString() {
		return "UserProductId{" +
				"user=" + user +
				", product=" + product +
				'}';
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


}