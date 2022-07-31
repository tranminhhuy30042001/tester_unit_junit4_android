package com.quanao.hanghieu.data;

import java.io.Serializable;

public class CartItem implements Serializable {
    String id;
    String name;
    String description;
    String imageView;
    String quantity;
    String price;

    public CartItem() {
        this.id = "id1";
        this.name = "name";
        this.description = "description";
        this.imageView = "imageView";
        this.quantity = "quantity";
        this.price = "price";
    }

    public CartItem(String id, String name, String description, String imageView, String quantity, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageView = imageView;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
