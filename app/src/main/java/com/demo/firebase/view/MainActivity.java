package com.demo.firebase.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.firebase.R;
import com.demo.firebase.model.MockInventory;
import com.demo.firebase.model.Product;
import com.demo.firebase.model.Section;

import java.util.Collection;

import static android.widget.LinearLayout.HORIZONTAL;
import static com.demo.firebase.model.Section.NEW_ARRIVAL;
import static com.demo.firebase.model.Section.RECOMMENDATION;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView newArrivalView = findViewById(R.id.new_arrivals_view);
        renderProducts(NEW_ARRIVAL, newArrivalView);
        RecyclerView recommendView = findViewById(R.id.recommendation_view);
        renderProducts(RECOMMENDATION, recommendView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // settings is the only option for now
        new GenderSelectionDialog(this).show();
        return true;
    }

    private void renderProducts(Section section, RecyclerView view) {
        view.setLayoutManager(new LinearLayoutManager(this, HORIZONTAL, false));
        Collection<Product> products = new MockInventory().productsForSection(section);
        ProductListAdapter adapter = new ProductListAdapter(products, R.layout.product_as_icons);
        view.setAdapter(adapter);
    }

    public void cartBtnClick(View v) {
        CartActivity.navigate(this);
    }

    public void recommendClick(View v) {
        SectionActivity.navigateToSectionPage(this, RECOMMENDATION);
    }

    public void newArrClick(View v) {
        SectionActivity.navigateToSectionPage(this, NEW_ARRIVAL);
    }

    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}

