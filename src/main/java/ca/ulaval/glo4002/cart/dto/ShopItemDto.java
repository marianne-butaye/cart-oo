package ca.ulaval.glo4002.cart.dto;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopItemDto {

  @XmlElement
  @JsonProperty
  private String itemSku;

  @XmlElement
  @JsonProperty
  private String name;

  @XmlElement
  @JsonProperty
  private boolean available;

  public ShopItemDto(String itemSku, String name, boolean available) {
    this.itemSku = itemSku;
    this.name = name;
    this.available = available;
  }

  public String getItemSku() {
    return itemSku;
  }

  public void setItemSku(String itemSku) {
    this.itemSku = itemSku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

}
