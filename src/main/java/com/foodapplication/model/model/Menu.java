package com.foodapplication.model.model;

import java.util.Arrays;
import java.util.Base64;

public class Menu {
	private int mid;
	private int rid;
	private String name;
	private String description;
	private int price;
	private int rating;
	private String is_available;
	private byte[] image;
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getIs_available() {
		return is_available;
	}
	public void setIs_available(String is_available) {
		this.is_available = is_available;
	}
	
	public Menu(int mid, int rid, String name, String description, int price, int rating) {
		super();
		this.mid = mid;
		this.rid = rid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		
	}
	 public String getBase64Image() {
	        if (image != null) {
	            return Base64.getEncoder().encodeToString(image);
	        }
	        return null; // Return null if the image is not set
	    }
	
	public Menu(int mid, int rid, String name, String description, int price, int rating, String is_available,
			byte[] image) {
		super();
		this.mid = mid;
		this.rid = rid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.is_available = is_available;
		this.image = image;
	}
	public Menu(int mid, int rid, String name, String description, int price, int rating, String is_available) {
		super();
		this.mid = mid;
		this.rid = rid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.is_available = is_available;
	}
	public Menu(int rid, String name, String description, int price, int rating, byte[] image) {
		super();
		this.rid = rid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.image = image;
	}
	public Menu() {
		super();
	}
	@Override
	public String toString() {
		return "Menu [mid=" + mid + ", rid=" + rid + ", name=" + name + ", description=" + description + ", price="
				+ price + ", rating=" + rating + ", is_available=" + is_available + ", image=" + Arrays.toString(image)
				+ "]";
	}
	
	
	
	
	
}
