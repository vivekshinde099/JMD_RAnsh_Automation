package com.jio.ranshjmd.Models;

import android.app.Activity;

public class Mylistdata {
    public Mylistdata(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Mylistdata(String issuename, Activity activity) {
        this.issuename = issuename;
        this.activity = activity;
    }

    public String getIssuename() {
        return issuename;
    }

    public void setIssuename(String issuename) {
        this.issuename = issuename;
    }

    private String issuename;
    private Activity activity;

}
