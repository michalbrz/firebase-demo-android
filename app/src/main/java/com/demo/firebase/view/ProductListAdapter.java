package com.demo.firebase.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.demo.firebase.R;
import com.demo.firebase.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter {

    private final List<Product> products;
    private final int layout;

    ProductListAdapter(Collection<Product> productList, int layoutResource) {
        products = new ArrayList<>(productList);
        layout = layoutResource;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        Product product = products.get(position);
        productHolder.image.setImageResource(product.imageId);
        productHolder.name.setText(product.name);
        productHolder.price.setText(product.priceAsString());
        productHolder.itemView.setOnClickListener(v -> {
            ProductActivity.navigateToProductPage((Activity) v.getContext(), product.id);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView price;

        ProductHolder(View view) {
            super(view);
            image = view.findViewById(R.id.thumbnail);
            name = view.findViewById(R.id.product_name);
            price = view.findViewById(R.id.price);
        }
    }
}
