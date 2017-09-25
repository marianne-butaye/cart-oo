package ca.ulaval.glo4002.cart.domain.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  public String ownerEmail;

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

  public List<CartItem> getItems() {
    return items;
  }

}
