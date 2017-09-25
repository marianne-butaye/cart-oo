package ca.ulaval.glo4002.cart.domain.shop;

public class ShopItem {

  private String itemSku;
  private String name;
  private boolean available;
  private double profitMarginPercentage;

  private ShopItem() {
    // JAXB
  }

  public ShopItem(String itemSku, String name, double profitMarginPercentage, boolean available) {
    this.itemSku = itemSku;
    this.name = name;
    this.profitMarginPercentage = profitMarginPercentage;
    this.available = available;
  }

  public String getName() {
    return name;
  }

  public boolean isAvailable() {
    return available;
  }

  public boolean hasSku(String sku) {
    return this.itemSku.equals(sku);
  }

  public String getItemSku() {
    return itemSku;
  }

  public double getProfitMarginPercentage() {
    return profitMarginPercentage;
  }

}
