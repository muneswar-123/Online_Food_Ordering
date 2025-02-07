package com.foodapplication.model.daoimpl;

import java.util.Map;
import com.foodapplication.model.model.CartItems;

public class cartDaoImp {

	public Map<Integer, CartItems> addItem(Map<Integer, CartItems> cart, CartItems newItem) {
	    if (cart.containsKey(newItem.getMenu_id())) {
	        // If item already exists, update the quantity
	        CartItems existingItem = cart.get(newItem.getMenu_id());
	        existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
	    } else {
	        // Add new item
	        cart.put(newItem.getMenu_id(), newItem);
	    }
	    return cart;
	}



    // Update item quantity in the cart
    public Map<Integer, CartItems> updateItem(Map<Integer, CartItems> cart, int menuId, int quantity) {
        if (cart.containsKey(menuId)) {
            // Validate quantity
            if (quantity <= 0) {
                // You can log the invalid quantity and return the cart without changes
                System.out.println("Invalid quantity: " + quantity);
                return cart;
            }
            cart.get(menuId).setQuantity(quantity);
        }
        return cart;
    }
    
    // Remove item from the cart
    public Map<Integer, CartItems> removeItem(Map<Integer, CartItems> cart, int menuId) {
        if (cart.containsKey(menuId)) {
            cart.remove(menuId);
        } else {
            // Optionally log if item was not found
            System.out.println("Item with menuId " + menuId + " not found in cart.");
        }
        return cart;
    }
}
