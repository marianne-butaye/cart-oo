package ca.ulaval.glo4002.cart;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.cart.application.ItemNotFoundException;
import ca.ulaval.glo4002.cart.application.LaunchType;
import ca.ulaval.glo4002.cart.application.Service;
import ca.ulaval.glo4002.cart.application.StorageType;
import ca.ulaval.glo4002.cart.application.serviceLocator.ServiceLocator;
import ca.ulaval.glo4002.cart.interfaces.rest.CartResource;
import ca.ulaval.glo4002.cart.interfaces.rest.PersistenceExceptionMapper;
import ca.ulaval.glo4002.cart.interfaces.rest.ShopResource;
import ca.ulaval.glo4002.cart.interfaces.rest.filters.CORSFilter;

public class CartServer implements Runnable {
  private static final int PORT = 7222;
  private static StorageType storageType;
  private static LaunchType launchType;
  private static boolean promoMode;

  public static void main(String[] args) {
    if (System.getProperty("store").equals("xml"))
      storageType = StorageType.XML;
    else
      storageType = StorageType.MEMORY;

    if (System.getProperty("mode").equals("demo"))
      launchType = LaunchType.DEMO;
    else
      launchType = LaunchType.PRODUCTION;

    if (System.getProperty("promo").equals("true"))
      promoMode = true;
    else
      promoMode = false;

    new CartServer().run();
  }

  public void run() {
    Service service = ServiceLocator.getService("CartApplicationService", storageType, launchType);
    service.execute();
    service = ServiceLocator.getService("ShopApplicationService", storageType, launchType);
    service.execute();

    Server server = new Server(PORT);
    ServletContextHandler contextHandler = new ServletContextHandler(server, "/");

    // Configuration manuelle au lieu du package scanning
    ResourceConfig packageConfig = new ResourceConfig()
        .registerInstances(createClientResource(), createCartResource())
        .registerInstances(new PersistenceExceptionMapper(), new ItemNotFoundException())
        .register(new CORSFilter());

    ServletContainer container = new ServletContainer(packageConfig);
    ServletHolder servletHolder = new ServletHolder(container);

    contextHandler.addServlet(servletHolder, "/*");

    try {
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      server.destroy();
    }
  }

  private CartResource createCartResource() {
    return new CartResource(storageType, launchType, promoMode);
  }

  private Object createClientResource() {
    return new ShopResource(storageType, launchType);
  }
}