package ca.ulaval.glo4002.cart.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;

import ca.ulaval.glo4002.cart.domain.cart.CartItem;

public class CartDto {

  public String ownerEmail;

  @XmlElementWrapper(name = "items")
  private List<CartItem> items = new ArrayList<>();

  public String getOwnerEmail() {
    return ownerEmail;
  }

  public void setOwnerEmail(String ownerEmail) {
    this.ownerEmail = ownerEmail;
  }

  public List<CartItem> getItems() {
    return items;
  }

  public void setItems(List<CartItem> items) {
    this.items = items;
  }

}
