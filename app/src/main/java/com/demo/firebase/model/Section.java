package com.demo.firebase.model;

public enum Section {

    NEW_ARRIVAL("NEW ARRIVALS"),
    RECOMMENDATION("JUST FOR YOU"),
    BEST_SELLER("BEST SELLERS");

    public final String title;

    Section(String name) {
        title = name;
    }
}
