package com.demo.firebase.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.firebase.R;
import com.demo.firebase.model.Cart;

public class CartActivity extends AppCompatActivity {

    //TODO field of type com.google.firebase.analytics.FirebaseAnalytics

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //TODO initialize FirebaseAnalytics

        Cart cart = Cart.getInstance();
        if (cart.isEmpty()) {
            TextView emptyCart = findViewById(R.id.emptycart);
            emptyCart.setText(R.string.cart_is_empty);
            Button checkoutBtn = findViewById(R.id.checkout_btn);
            checkoutBtn.setVisibility(View.INVISIBLE);
        } else {
            RecyclerView view = findViewById(R.id.item_list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            view.setLayoutManager(layoutManager);
            view.setAdapter(new CartItemAdapter());
        }
    }


    public void checkout(View v) {
        Cart cart = Cart.getInstance();

        //track conversion for purchase
        logPurchase(cart.getSubTotal());

        cart.pay();
        Intent intent = new Intent(this, PaidActivity.class);
        startActivity(intent);
    }

    // Track the conversion with the purchase subtotal
    private void logPurchase(double subtotal) {
        //TODO FirebaseAnalytics.Event.PURCHASE
    }

    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, CartActivity.class);
        activity.startActivity(intent);
    }
}
