package ca.ulaval.glo4002.cart.application.serviceLocator;

import ca.ulaval.glo4002.cart.application.LaunchType;
import ca.ulaval.glo4002.cart.application.Service;
import ca.ulaval.glo4002.cart.application.StorageType;

public class ServiceLocator {

  private static Cache cache;

  static {
    cache = new Cache();
  }

  public static Service getService(String jndiName, StorageType storageType,
      LaunchType launchType) {

    Service service = cache.getService(jndiName);

    if (service != null) {
      return service;
    }

    InitialContext context = new InitialContext(storageType, launchType);
    Service service1 = (Service) context.lookup(jndiName);
    cache.addService(service1);
    return service1;
  }

}
