package com.demo.firebase.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.firebase.R;
import com.demo.firebase.model.Cart;
import com.demo.firebase.model.MockInventory;
import com.demo.firebase.model.Product;

public class ProductActivity extends AppCompatActivity {

    private static final String PRODUCT_ID = "product_id";

    private Product product;
    private Spinner quantitySpinner;

    //TODO create field of type com.google.firebase.analytics.FirebaseAnalytics

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        //TODO initialize FirebaseAnalytics

        TextView name = findViewById(R.id.product_name);
        TextView price = findViewById(R.id.product_price);
        ImageView image = findViewById(R.id.product_image);

        quantitySpinner = findViewById(R.id.quantity_selector);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.quantity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);

        product = new MockInventory().getProductById(getProductIdFromIntent());
        if (product != null) {
            image.setImageResource(product.imageId);
            name.setText(product.name);
            price.setText(product.priceAsString());

            //TODO FirebaseAnalytics.Event.VIEW_ITEM
        } else {
            //wrong product id
            new AlertDialog.Builder(this)
                    .setMessage(R.string.item_not_found)
                    .setPositiveButton(R.string.continue_shop, (dialog, id) -> MainActivity.navigate(this))
                    .create().show();
        }
    }

    private int getProductIdFromIntent() {
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            //handle Deep links: extract product id from the "id" parameter of the URI
            return Integer.parseInt(data.getQueryParameter("id"));
        } else {
            return intent.getIntExtra(PRODUCT_ID, -1);
        }
    }

    public void addToCart(View v) {
        int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());
        Cart.getInstance().add(product, quantity);

        //TODO FirebaseAnalytics.Event.ADD_TO_CART

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_added_cart)
                .setPositiveButton(R.string.view_cart, (dialog, id) -> CartActivity.navigate(this))
                .setNegativeButton(R.string.continue_shop, (dialog, id) -> MainActivity.navigate(this));
        builder.create().show();
    }

    public static void navigateToProductPage(Activity activity, int productId) {
        Intent intent = new Intent(activity, ProductActivity.class);
        intent.putExtra(PRODUCT_ID, productId);
        activity.startActivity(intent);
    }
}
