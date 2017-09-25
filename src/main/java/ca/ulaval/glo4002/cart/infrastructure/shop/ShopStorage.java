package ca.ulaval.glo4002.cart.infrastructure.shop;

import java.util.List;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

public interface ShopStorage {

  public List<ShopItem> readShop();

  public void persistShop(List<ShopItem> items);

}
