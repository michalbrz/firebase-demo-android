package com.demo.firebase.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.firebase.R;
import com.demo.firebase.model.MockInventory;
import com.demo.firebase.model.Product;
import com.demo.firebase.model.Section;

import java.util.Collection;

import static android.widget.LinearLayout.VERTICAL;

public class SectionActivity extends AppCompatActivity {

    private static final String SECTION_NAME = "section";

    //TODO field of type com.google.firebase.analytics.FirebaseAnalytics

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        //TODO initialize FirebaseAnalytics

        TextView title = findViewById(R.id.title);
        try {
            Section section = Section.valueOf(getSectionFromIntent());
            title.setText(section.title);
            setUpProductsList(section);

            //TODO FirebaseAnalytics.Event.VIEW_ITEM_LIST

        } catch (IllegalArgumentException e) {
            showPageDoesNotExistDialog();
        }
    }

    private void showPageDoesNotExistDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.page_not_found)
                .setPositiveButton(R.string.continue_shop, (dialog, id) -> MainActivity.navigate(this));
        builder.create().show();
    }

    private void setUpProductsList(Section section) {
        RecyclerView view = findViewById(R.id.section_product_list);
        view.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        Collection<Product> products = new MockInventory().productsForSection(section);
        ProductListAdapter adapter = new ProductListAdapter(products, R.layout.product_as_list);
        view.setAdapter(adapter);
    }

    private String getSectionFromIntent() {
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            //handle Deep links: extract section name from the last segment of the URI
            return data.getLastPathSegment().toUpperCase();
        } else {
            return intent.getStringExtra(SECTION_NAME).toUpperCase();
        }
    }

    public void cartBtnClick(View v) {
        CartActivity.navigate(this);
    }

    public static void navigateToSectionPage(Activity activity, Section section) {
        Intent intent = new Intent(activity, SectionActivity.class);
        intent.putExtra(SECTION_NAME, section.toString());
        activity.startActivity(intent);
    }
}
