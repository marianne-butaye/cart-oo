package ca.ulaval.glo4002.cart.application;

import java.util.List;

import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.cart.CartItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.infrastructure.cart.CartRepository;
import ca.ulaval.glo4002.cart.infrastructure.cart.CartRepositoryInMemory;
import ca.ulaval.glo4002.cart.infrastructure.cart.CartRepositoryXML;

public class CartApplicationService {

  private CartRepository cartStorage;

  public CartApplicationService(StorageType storageType) {
    switch (storageType) {
    case XML:
      cartStorage = new CartRepositoryXML();
      break;
    case MEMORY:
      cartStorage = new CartRepositoryInMemory();
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

  public void addItemsToCart(String email, ShopItem item, int quantity) {
    List<Cart> carts = cartStorage.readCarts();
    Cart cart = getCartByOwner(email, carts);

    cart.addItem(new CartItem(item.getName(), quantity));

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
