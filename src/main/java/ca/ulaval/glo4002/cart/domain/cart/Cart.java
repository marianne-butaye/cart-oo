package ca.ulaval.glo4002.cart.domain.cart;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;

public class Cart {

	public String ownerEmail;

	@XmlElementWrapper(name = "items")
	private List<CartItem> items = new ArrayList<>();

	private Cart() {
		// JAXB
	}

	public Cart(String email) {
		this.ownerEmail = email;
	}

	public void addItem(CartItem item) {
		this.items.add(item);
	}

}
