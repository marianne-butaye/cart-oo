package ca.ulaval.glo4002.cart.infrastructure.cart;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cart.domain.cart.Cart;

public class CartInMemory implements CartStorage {

  private List<Cart> carts = new ArrayList<>();

  @Override
  public List<Cart> readCarts() {
    return carts;
  }

  @Override
  public void persistCarts(List<Cart> carts) {
    this.carts = carts;
  }
}
