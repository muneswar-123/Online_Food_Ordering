package com.foodapplication.model.model;

public class CartItems {
	private int cartItem;
	private int menu_id;
	private int restaurant_id;
	private String name ;
	private int quantity;
	private int price;
	
	@Override
	public String toString() {
		return "CartItems [cartItem=" + cartItem + ", menu_id=" + menu_id + ", restaurant_id=" + restaurant_id
				+ ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
	public int getCartItem() {
		return cartItem;
	}
	public void setCartItem(int cartItem) {
		this.cartItem = cartItem;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public CartItems(int cartItem, int menu_id, int restaurant_id, String name, int quantity, int price) {
		super();
		this.cartItem = cartItem;
		this.menu_id = menu_id;
		this.restaurant_id = restaurant_id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	public CartItems(int menu_id, int restaurant_id, String name, int quantity, int price) {
		super();
		this.menu_id = menu_id;
		this.restaurant_id = restaurant_id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	
	
}
