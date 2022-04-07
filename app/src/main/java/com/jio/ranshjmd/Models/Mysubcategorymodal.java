package com.jio.ranshjmd.Models;

import android.app.Activity;

public class Mysubcategorymodal {

    public Mysubcategorymodal(String subcategoryissuename, Activity activity) {
        this.subcategoryissuename = subcategoryissuename;
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getSubcategoryissuename() {
        return subcategoryissuename;
    }

    public void setSubcategoryissuename(String subcategoryissuename) {
        this.subcategoryissuename = subcategoryissuename;
    }

    private String subcategoryissuename;
    private Activity activity;

}

