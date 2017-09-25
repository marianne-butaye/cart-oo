package ca.ulaval.glo4002.cart.application.serviceLocator;

import ca.ulaval.glo4002.cart.application.CartApplicationService;
import ca.ulaval.glo4002.cart.application.LaunchType;
import ca.ulaval.glo4002.cart.application.ShopApplicationService;
import ca.ulaval.glo4002.cart.application.StorageType;

public class InitialContext {

  private StorageType storageType;
  private LaunchType launchType;

  public InitialContext(StorageType storageType, LaunchType launchType) {
    super();
    this.storageType = storageType;
    this.launchType = launchType;
  }

  public Object lookup(String jndiName) {
    if (jndiName.equalsIgnoreCase("CartApplicationService")) {
      System.out.println("Looking up and creating a new CartApplicationService object");
      return new CartApplicationService(storageType);
    } else if (jndiName.equalsIgnoreCase("ShopApplicationService")) {
      System.out.println("Looking up and creating a new ShopApplicationService object");
      return new ShopApplicationService(storageType, launchType);
    }
    return null;
  }

}
