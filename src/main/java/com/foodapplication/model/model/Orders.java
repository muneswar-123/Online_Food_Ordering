package com.foodapplication.model.model;

public class Orders {
    private int orderID;
    private int userID;
    private int restaurantId;
    private int menuId;
    private int quantity;
    private String paymentMethod;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Orders(int orderID, int userID, int restaurantId, int menuId, int quantity, String paymentMethod) {
        super();
        this.orderID = orderID;
        this.userID = userID;
        this.restaurantId = restaurantId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
    }

    public Orders(int userID, int restaurantId, int menuId, int quantity, String paymentMethod) {
        super();
        this.userID = userID;
        this.restaurantId = restaurantId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
    }

    public Orders() {
        super();
    }

    @Override
    public String toString() {
        return "Orders [orderID=" + orderID + ", userID=" + userID + ", restaurantId=" + restaurantId + ", menuId="
                + menuId + ", quantity=" + quantity + ", paymentMethod=" + paymentMethod + "]";
    }
}
