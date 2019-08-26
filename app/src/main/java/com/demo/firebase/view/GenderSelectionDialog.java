package com.demo.firebase.view;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceManager;

class GenderSelectionDialog {

    private static final String[] GENDERS = new String[]{"Male", "Female"};
    private static final String GENDER_PREF_KEY = "gender_pref_key";

    private Context context;
    private SharedPreferences sharedPreferences;

    GenderSelectionDialog(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    void showIfNecessary() {
        if (!sharedPreferences.contains(GENDER_PREF_KEY)) {
            new AlertDialog.Builder(context)
                    .setTitle("Select clothes to display")
                    .setItems(GENDERS, (dialog, which) -> saveGenderSelection(GENDERS[which]))
                    .create()
                    .show();
        }
    }

    private void saveGenderSelection(String gender) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(GENDER_PREF_KEY, gender).apply();

        //TODO set Firebase User Property
    }
}
