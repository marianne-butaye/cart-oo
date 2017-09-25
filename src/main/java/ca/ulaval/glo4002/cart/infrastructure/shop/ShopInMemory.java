package ca.ulaval.glo4002.cart.infrastructure.shop;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

public class ShopInMemory implements ShopStorage {

  private List<ShopItem> items = new ArrayList<>();

  @Override
  public List<ShopItem> readShop() {
    return items;
  }

  @Override
  public void persistShop(List<ShopItem> items) {
    this.items = items;
  }
}
