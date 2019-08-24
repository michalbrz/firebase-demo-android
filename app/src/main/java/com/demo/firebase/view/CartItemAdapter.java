package com.demo.firebase.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.demo.firebase.R;
import com.demo.firebase.model.Cart;
import com.demo.firebase.model.Product;

public class CartItemAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cart_item_list, parent, false);
        return new CartItemHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartItemHolder itemHolder = (CartItemHolder) holder;
        Cart cart  = Cart.getInstance();
        final Product product = cart.getProduct(position);
        itemHolder.image.setImageResource(product.imageId);
        itemHolder.name.setText(product.name);
        itemHolder.price.setText(product.priceAsString());
        itemHolder.quantity.setText("Quantity: " + cart.getQuantity(product));
    }

    @Override
    public int getItemCount() {
        return Cart.getInstance().getNumOfUniqueProducts();
    }

    private class CartItemHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;
        TextView quantity;

        public CartItemHolder(View view) {
            super(view);
            image = view.findViewById(R.id.thumbnail);
            name = view.findViewById(R.id.product_name);
            price = view.findViewById(R.id.price);
            quantity = view.findViewById(R.id.quantity);
        }
    }
}