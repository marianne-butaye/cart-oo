package ca.ulaval.glo4002.cart.modules;

import com.google.inject.AbstractModule;

import ca.ulaval.glo4002.cart.application.LaunchType;
import ca.ulaval.glo4002.cart.application.StorageType;

public class ShopModule extends AbstractModule {

  private StorageType storageType;
  private LaunchType launchType;

  public ShopModule(StorageType storageType, LaunchType launchType) {
    super();
    this.storageType = storageType;
    this.launchType = launchType;
  }

  @Override
  protected void configure() {
    bind(StorageType.class).toInstance(storageType);
    bind(LaunchType.class).toInstance(launchType);
  }

}
