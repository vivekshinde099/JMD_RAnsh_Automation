package com.jio.ranshjmd.Models;

public class Getuserdatamasterdata {
    private String name;
    private String prm;
    private String mobile;
    private String email;
    private String make_model;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrm() {
        return prm;
    }

    public void setPrm(String prm) {
        this.prm = prm;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMake_model() {
        return make_model;
    }

    public void setMake_model(String make_model) {
        this.make_model = make_model;
    }

    public Getuserdatamasterdata(String name, String prm, String mobile, String email, String make_model) {
        this.name = name;
        this.prm = prm;
        this.mobile = mobile;
        this.email = email;
        this.make_model = make_model;
    }

    public Getuserdatamasterdata() {
    }
}
