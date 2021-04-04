package com.aioki.myapplication.Site;

import androidx.annotation.NonNull;

public class SiteModel {

    private final int id;
    private final String url;
    private final String name;

    public SiteModel(String name, String url) {
        this.name = name;
        this.url = url;
        id = 0;
    }

    public SiteModel(String name, String url, int id) {
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
