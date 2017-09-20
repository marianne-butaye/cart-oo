package ca.ulaval.glo4002.cart.domain.cart;

import javax.xml.bind.annotation.XmlElement;

public class CartItem {

	@XmlElement
	private String name;

	@XmlElement
	private int quantity;

	private CartItem() {
		// JAXB
	}

	public CartItem(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}
}
