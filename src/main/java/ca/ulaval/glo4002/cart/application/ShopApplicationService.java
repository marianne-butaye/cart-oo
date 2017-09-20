package ca.ulaval.glo4002.cart.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

public class ShopApplicationService {
	private static final String SHOP_STORAGE = "shop";
	private static File storageFile;

	static {
		storageFile = XmlUtils.createStorageFile(SHOP_STORAGE);
	}

	public ShopApplicationService() {
	}

	public List<ShopItem> listAvailableItems() {
		List<ShopItem> items = readShopFromFile();
		if (items.isEmpty()) {
			Logger.getGlobal().info("Prefilling data in the shop for the demo");
			prefillDemoData();
			items = readShopFromFile();
		}

		return items.stream().filter(i -> i.isAvailable()).collect(Collectors.toList());
	}

	public ShopItem findItemBySku(String sku) {
		return listAvailableItems().stream().filter(x -> x.hasSku(sku)).findFirst()
				.orElseThrow(ItemNotFoundException::new);
	}

	private List<ShopItem> readShopFromFile() {
		Unmarshaller unmarshaller = XmlUtils.createUnmarshaller();
		try {
			return ((Shop) unmarshaller.unmarshal(storageFile)).getItems();
		} catch (JAXBException e) {
			return new ArrayList<>();
		}
	}

	private void persistShop(List<ShopItem> items) {
		Marshaller marshaller = XmlUtils.createMarshaller();
		try {
			marshaller.marshal(new Shop(items), storageFile);
		} catch (JAXBException e) {
			throw new PersistenceException(e);
		}
	}

	private void prefillDemoData() {
		addItem("1251521", "Peanuts", 1.20, true);
		addItem("236637", "Clean Code", 0.50, false);
		addItem("235265", "Détendeur Mares Abyss Navy 22", 0.15, true);
		addItem("276101", "Imprimante 3D", 0.60, true);
		addItem("818113", "GoPro", 4.60, true);
		addItem("51-153", "Peinture à numéro", 1.40, true);
	}

	private void addItem(String sku, String name, double margin, boolean available) {
		List<ShopItem> items = readShopFromFile();
		items.add(new ShopItem(sku, name, margin, available));

		persistShop(items);
	}
}
