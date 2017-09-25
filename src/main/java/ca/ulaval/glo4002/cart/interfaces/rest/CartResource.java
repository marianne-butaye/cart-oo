package ca.ulaval.glo4002.cart.interfaces.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.cart.application.CartApplicationService;
import ca.ulaval.glo4002.cart.application.CartAssembler;
import ca.ulaval.glo4002.cart.application.LaunchType;
import ca.ulaval.glo4002.cart.application.ShopApplicationService;
import ca.ulaval.glo4002.cart.application.StorageType;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.dto.CartDto;

@Path("/clients/{" + CartResource.EMAIL_PARAMETER + "}/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
  public static final String EMAIL_PARAMETER = "email";
  public static final String SKU_PARAMETER = "sku";

  private CartApplicationService cartService;
  private ShopApplicationService shopService;
  private CartAssembler cartAssembler;
  private boolean promoMode;

  public CartResource(StorageType storageType, LaunchType launchType, boolean promoMode) {
    this.cartService = new CartApplicationService(storageType);
    this.shopService = new ShopApplicationService(storageType, launchType);
    this.cartAssembler = new CartAssembler();
    this.promoMode = promoMode;
  }

  @GET
  public CartDto getCart(@PathParam(EMAIL_PARAMETER) String email) {
    return cartAssembler.toDto(cartService.findOrCreateCartForClient(email));
  }

  @PUT
  @Path("/{" + SKU_PARAMETER + "}")
  public Response addItemToCart(@PathParam(EMAIL_PARAMETER) String email,
      @PathParam(SKU_PARAMETER) String sku) {
    // TODO this resource does too much
    ShopItem shopItem = shopService.findItemBySku(sku);
    int quantity = 1;
    if (promoMode)
      quantity = 2;
    cartService.addItemsToCart(email, shopItem, quantity);
    return Response.ok().build();
  }
}
