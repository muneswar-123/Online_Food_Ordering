package com.foodapplication.model.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Restaurant implements Serializable {
	private int rid;
	private String name;
	private String cusineeType;	
	private String address;	
	private int rating;
	private Timestamp deliverTime;
	private String isAchive;
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
	public String getCusineeType() {
		return cusineeType;
	}
	public void setCusineeType(String cusineeType) {
		this.cusineeType = cusineeType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getIaAchive() {
		return isAchive;
	}
	public void setIaAchive(String iaAchive) {
		this.isAchive = iaAchive;
	}
	public Restaurant(int rid, String name, String cusineeType, String address, int rating) {
		super();
		this.rid = rid;
		this.name = name;
		this.cusineeType = cusineeType;
		this.address = address;
		this.rating = rating;
	}
	public Restaurant(int rid, String name, String cusineeType, String address, int rating, Timestamp deliverTime,
			String isAchive) {
		super();
		this.rid = rid;
		this.name = name;
		this.cusineeType = cusineeType;
		this.address = address;
		this.rating = rating;
		this.deliverTime = deliverTime;
		this.isAchive = isAchive;
	}
	public Restaurant(String name, String cusineeType, String address, int rating, String isAchive) {
		super();
		this.name = name;
		this.cusineeType = cusineeType;
		this.address = address;
		this.rating = rating;
		this.isAchive = isAchive;
	}
	public Restaurant(String name, String cusineeType, String address, int rating) {
		super();
		this.name = name;
		this.cusineeType = cusineeType;
		this.address = address;
		this.rating = rating;
	}
	public Restaurant() {
		super();
	}
	@Override
	public String toString() {
		return rid + "   "+ name + "   " + cusineeType + "   " + address+ "   " + rating + "   " + deliverTime + "   " + isAchive;
	}
}

