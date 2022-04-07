package com.jio.ranshjmd.ApiConstructor;

public class AutoresListConstructor {
    private String intid;
    private String mob;

    public AutoresListConstructor (String intid) {
        this.intid = intid;
    }

    public String getIntid() {
        return intid;
    }

    public void setIntid(String intid) {
        this.intid = intid;
    }
}
