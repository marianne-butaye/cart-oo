package ca.ulaval.glo4002.cart.interfaces.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.inject.Inject;

import ca.ulaval.glo4002.cart.application.ShopApplicationService;
import ca.ulaval.glo4002.cart.application.ShopAssembler;
import ca.ulaval.glo4002.cart.dto.ShopItemDto;

@Path("/shop")
public class ShopResource {

  private ShopApplicationService shopService;
  private ShopAssembler shopAssembler;

  @Inject
  public ShopResource(ShopApplicationService shopService, ShopAssembler shopAssembler) {
    this.shopService = shopService;
    this.shopAssembler = shopAssembler;
  }

  @GET
  @Path("/available-items")
  public List<ShopItemDto> listItems() {
    return shopAssembler.toDto(shopService.listAvailableItems());
  }
}
