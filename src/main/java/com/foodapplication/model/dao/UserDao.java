package com.foodapplication.model.dao;
import java.util.List;

import com.foodapplication.model.model.User;


public interface UserDao {
	void setUser(User u);
	List<User> fetchAll();
	User getUser(String email);
	User getUser(int id);
	void update(int id , String name);
	int delete(int id);
}
