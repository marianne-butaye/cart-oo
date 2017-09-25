package ca.ulaval.glo4002.cart.application;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.dto.ShopItemDto;

public class ShopAssembler {

  public List<ShopItemDto> toDto(List<ShopItem> items) {
    List<ShopItemDto> itemsDto = new ArrayList<>();
    for (ShopItem item : items)
      itemsDto.add(toDto(item));
    return itemsDto;
  }

  public ShopItemDto toDto(ShopItem item) {
    ShopItemDto itemDto = new ShopItemDto(item.getItemSku(), item.getName(), item.isAvailable());
    return itemDto;
  }

}
