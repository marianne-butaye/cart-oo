package ca.ulaval.glo4002.cart.application;

import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.cart.CartItem;
import ca.ulaval.glo4002.cart.dto.CartDto;

public class CartAssembler {

  public Cart fromDto(CartDto cartDto) {
    Cart cart = new Cart(cartDto.getOwnerEmail());
    for (CartItem item : cartDto.getItems())
      cart.addItem(item);
    return cart;
  }

  public CartDto toDto(Cart cart) {
    CartDto cartDto = new CartDto();
    cartDto.setOwnerEmail(cart.ownerEmail);
    cartDto.setItems(cart.getItems());
    return cartDto;
  }

}
