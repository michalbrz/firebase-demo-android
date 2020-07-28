package com.demo.firebase.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {

    public final int id;
    public final String name;
    public final int imageId;
    public final double price;
    final Category category;

    public Product(int id, String name, Category category, double price,
                   int imageResourceId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageId = imageResourceId;
    }

    public String priceAsString() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(price);
    }
}
