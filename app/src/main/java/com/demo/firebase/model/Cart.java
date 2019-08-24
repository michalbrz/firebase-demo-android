package com.demo.firebase.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static Cart instance = null;
    private final List<CartItem> products = new ArrayList<>();

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    private Cart() { }

    public void add(Product product, int quantity) {
        for (CartItem item : products) {
            if (item.product.equals(product)) {
                item.updateQuantity(quantity);
                return;
            }
        }
        products.add(new CartItem(product, quantity));
    }

    public double getSubTotal() {
        double total = 0;
        for (CartItem item : products) {
            total += item.product.price * item.quantity;
        }
        return total;
    }

    public void pay() {
        products.clear();
    }

    /**
     * Return the product at the specific position (starts from 0)
     */
    public Product getProduct(int pos) {
        return products.get(pos).product;
    }

    public int getQuantity(Product product) {
        for (CartItem cartItem : products) {
            if (cartItem.product.equals(product)) {
                return cartItem.quantity;
            }
        }
        return 0;
    }

    public int getNumOfUniqueProducts() {
        return products.size();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    private class CartItem {
        final Product product;
        int quantity;

        CartItem(Product p, int quantity) {
            product = p;
            this.quantity = quantity;
        }

        void updateQuantity(int increased) {
            quantity += increased;
        }
    }
}
