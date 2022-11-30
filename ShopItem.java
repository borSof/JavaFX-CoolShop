package cool_shop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

public class ShopItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5066214906675873794L;
	private int item_id;
	private String item_name;
	private float item_price;

	public ShopItem(int item_id, String item_name, float item_price) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_price = item_price;
	}

	public ShopItem() {
		super();
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public float getItem_price() {
		return item_price;
	}

	public void setItem_price(float item_price) {
		this.item_price = item_price;
	}

	@Override
	public String toString() {
		return "ShopItem [item_id=" + item_id + ", item_name=" + item_name + ", item_price=" + item_price + "]";
	}
}
