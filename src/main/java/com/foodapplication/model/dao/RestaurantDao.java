package com.foodapplication.model.dao;

import java.util.List;

import com.foodapplication.model.model.Restaurant;


public interface RestaurantDao {
	void insert(Restaurant restaurant) throws Exception;
	List<Restaurant> fetchAll()throws Exception;
	void fetchSpecific(String name) throws Exception;
	void update(int id , String name)throws Exception;
	void delete(int id)throws Exception ;
}

