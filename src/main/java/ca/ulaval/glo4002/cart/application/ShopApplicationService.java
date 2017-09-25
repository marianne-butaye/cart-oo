package ca.ulaval.glo4002.cart.application;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.infrastructure.shop.ShopInMemory;
import ca.ulaval.glo4002.cart.infrastructure.shop.ShopStorage;
import ca.ulaval.glo4002.cart.infrastructure.shop.ShopXML;

public class ShopApplicationService {
  private ShopStorage shopStorage;
  private LaunchType launchType;

  public ShopApplicationService(StorageType storageType, LaunchType launchType) {
    this.launchType = launchType;
    switch (storageType) {
    case XML:
      shopStorage = new ShopXML();
      break;
    case MEMORY:
      shopStorage = new ShopInMemory();
      break;
    default:
      break;
    }
  }

  public List<ShopItem> listAvailableItems() {
    List<ShopItem> items = shopStorage.readShop();
    if (items.isEmpty() && launchType.equals(LaunchType.DEMO)) {
      Logger.getGlobal().info("Prefilling data in the shop for the demo");
      prefillDemoData();
      items = shopStorage.readShop();
    }

    return items.stream().filter(i -> i.isAvailable()).collect(Collectors.toList());
  }

  public ShopItem findItemBySku(String sku) {
    return listAvailableItems().stream().filter(x -> x.hasSku(sku)).findFirst()
        .orElseThrow(ItemNotFoundException::new);
  }

  private void prefillDemoData() {
    addItem("1251521", "Peanuts", 1.20, true);
    addItem("236637", "Clean Code", 0.50, false);
    addItem("235265", "Détendeur Mares Abyss Navy 22", 0.15, true);
    addItem("276101", "Imprimante 3D", 0.60, true);
    addItem("818113", "GoPro", 4.60, true);
    addItem("51-153", "Peinture à numéro", 1.40, true);
  }

  private void addItem(String sku, String name, double margin, boolean available) {
    List<ShopItem> items = shopStorage.readShop();
    items.add(new ShopItem(sku, name, margin, available));

    shopStorage.persistShop(items);
  }
}
