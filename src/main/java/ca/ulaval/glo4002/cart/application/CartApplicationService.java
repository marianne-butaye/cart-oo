package ca.ulaval.glo4002.cart.application;

import java.util.List;

import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.cart.CartItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.infrastructure.cart.CartInMemory;
import ca.ulaval.glo4002.cart.infrastructure.cart.CartStorage;
import ca.ulaval.glo4002.cart.infrastructure.cart.CartXML;

public class CartApplicationService {

  private CartStorage cartStorage;

  public CartApplicationService(StorageType storageType) {
    switch (storageType) {
    case XML:
      cartStorage = new CartXML();
      break;
    case MEMORY:
      cartStorage = new CartInMemory();
      break;
    default:
      break;
    }
  }

  public Cart findOrCreateCartForClient(String email) {
    List<Cart> carts = cartStorage.readCarts();

    Cart cart = getCartByOwner(email, carts);

    return cart;
  }

  public void addItemToCart(String email, ShopItem item) {
    List<Cart> carts = cartStorage.readCarts();
    Cart cart = getCartByOwner(email, carts);

    cart.addItem(new CartItem(item.getName(), 1));

    cartStorage.persistCarts(carts);
  }

  public List<Cart> findAllCarts() {
    return this.cartStorage.readCarts();
  }

  private Cart getCartByOwner(String email, List<Cart> carts) {
    return carts.stream().filter(c -> c.ownerEmail.equals(email)).findFirst().orElseGet(() -> {
      Cart newCart = new Cart(email);
      carts.add(newCart);
      cartStorage.persistCarts(carts);
      return newCart;
    });
  }

}
