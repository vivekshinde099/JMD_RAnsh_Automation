package com.jio.ranshjmd.ApiConstructor;

public class AutoResTicketConstructor {

    private String intid;
    private String ar_dt;
    private String ar_eml;
    private String ar_title;
    private String mob;
    private String ar_name;
    private String ar_prm;
    private String ar_status;
    private String ar_fdcomm;
    private String ar_fddt;
    private String ar_fdtit;

    public AutoResTicketConstructor(String ar_dt, String ar_eml, String ar_title, String mob, String ar_name, String ar_prm, String ar_status, String ar_fdcomm, String ar_fddt, String ar_fdtit) {
        this.ar_dt = ar_dt;
        this.ar_eml = ar_eml;
        this.ar_title = ar_title;
        this.mob = mob;
        this.ar_name = ar_name;
        this.ar_prm = ar_prm;
        this.ar_status = ar_status;
        this.ar_fdcomm = ar_fdcomm;
        this.ar_fddt = ar_fddt;
        this.ar_fdtit = ar_fdtit;
    }

    public AutoResTicketConstructor(String ar_fdcomm, String ar_fddt, String ar_fdtit) {
        this.ar_fdcomm = ar_fdcomm;
        this.ar_fddt = ar_fddt;
        this.ar_fdtit = ar_fdtit;
    }

    public String getIntid() {
        return intid;
    }

    public void setIntid(String intid) {
        this.intid = intid;
    }

    public String getAr_dt() {
        return ar_dt;
    }

    public void setAr_dt(String ar_dt) {
        this.ar_dt = ar_dt;
    }

    public String getAr_eml() {
        return ar_eml;
    }

    public void setAr_eml(String ar_eml) {
        this.ar_eml = ar_eml;
    }

    public String getAr_title() {
        return ar_title;
    }

    public void setAr_title(String ar_title) {
        this.ar_title = ar_title;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getAr_name() {
        return ar_name;
    }

    public void setAr_name(String ar_name) {
        this.ar_name = ar_name;
    }

    public String getAr_prm() {
        return ar_prm;
    }

    public void setAr_prm(String ar_prm) {
        this.ar_prm = ar_prm;
    }

    public String getAr_status() {
        return ar_status;
    }

    public void setAr_status(String ar_status) {
        this.ar_status = ar_status;
    }

    public String getAr_fdcomm() {
        return ar_fdcomm;
    }

    public void setAr_fdcomm(String ar_fdcomm) {
        this.ar_fdcomm = ar_fdcomm;
    }

    public String getAr_fddt() {
        return ar_fddt;
    }

    public void setAr_fddt(String ar_fddt) {
        this.ar_fddt = ar_fddt;
    }

    public String getAr_fdtit() {
        return ar_fdtit;
    }

    public void setAr_fdtit(String ar_fdtit) {
        this.ar_fdtit = ar_fdtit;
    }
}
