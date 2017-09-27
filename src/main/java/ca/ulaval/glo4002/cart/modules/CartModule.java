package ca.ulaval.glo4002.cart.modules;

import com.google.inject.AbstractModule;

import ca.ulaval.glo4002.cart.application.LaunchType;
import ca.ulaval.glo4002.cart.application.StorageType;

public class CartModule extends AbstractModule {

  private StorageType storageType;
  private LaunchType launchType;
  private boolean promoMode;

  public CartModule(StorageType storageType, LaunchType launchType, boolean promoMode) {
    super();
    this.storageType = storageType;
    this.launchType = launchType;
    this.promoMode = promoMode;
  }

  @Override
  protected void configure() {
    bind(StorageType.class).toInstance(storageType);
    bind(LaunchType.class).toInstance(launchType);
    bind(boolean.class).toInstance(promoMode);
  }

}
