package ca.ulaval.glo4002.cart.domain.shop;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class ShopItem {

	@XmlElement
	@JsonProperty
	private String itemSku;

	@XmlElement
	@JsonProperty
	private String name;

	@XmlElement
	@JsonProperty
	private boolean available;
	
	@XmlElement
	@JsonProperty
	// Ne pas enlever @JsonProperty, ceci sert pour le panneau d'admin où on saisit les items
	private double profitMarginPercentage;

	private ShopItem() {
		// JAXB
	}

	public ShopItem(String itemSku, String name, double profitMarginPercentage, boolean available) {
		this.itemSku = itemSku;
		this.name = name;
		this.profitMarginPercentage = profitMarginPercentage;
		this.available = available;
	}

	public String getName() {
		return name;
	}

	public boolean isAvailable() {
		return available;
	}

	public boolean hasSku(String sku) {
		return this.itemSku.equals(sku);
	}

}
