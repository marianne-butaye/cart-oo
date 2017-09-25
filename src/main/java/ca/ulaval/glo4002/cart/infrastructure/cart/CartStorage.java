package ca.ulaval.glo4002.cart.infrastructure.cart;

import java.util.List;

import ca.ulaval.glo4002.cart.domain.cart.Cart;

public interface CartStorage {

  public List<Cart> readCarts();

  public void persistCarts(List<Cart> carts);

}
