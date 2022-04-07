package com.jio.ranshjmd.ApiConstructor;

public class UserProfileDataConstructor {

    private String ID;
    private String umd_key;
    private String umd_circ;
    private String umd_dt;
    private String umd_eml;
    private String umd_mob;
    private String umd_mmm;
    private String umd_name;
    private String umd_pos;
    private String umd_prm;
    private String umd_udi;
    private String umd_zone;
    private String umd_category;

    public UserProfileDataConstructor(String umd_key, String umd_circ, String umd_dt, String umd_eml, String umd_mob, String umd_mmm, String umd_name, String umd_pos, String umd_prm, String umd_udi, String umd_zone, String umd_category) {
        this.umd_key = umd_key;
        this.umd_circ = umd_circ;
        this.umd_dt = umd_dt;
        this.umd_eml = umd_eml;
        this.umd_mob = umd_mob;
        this.umd_mmm = umd_mmm;
        this.umd_name = umd_name;
        this.umd_pos = umd_pos;
        this.umd_prm = umd_prm;
        this.umd_udi = umd_udi;
        this.umd_zone = umd_zone;
        this.umd_category = umd_category;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUmd_key() {
        return umd_key;
    }

    public void setUmd_key(String umd_key) {
        this.umd_key = umd_key;
    }

    public String getUmd_circ() {
        return umd_circ;
    }

    public void setUmd_circ(String umd_circ) {
        this.umd_circ = umd_circ;
    }

    public String getUmd_dt() {
        return umd_dt;
    }

    public void setUmd_dt(String umd_dt) {
        this.umd_dt = umd_dt;
    }

    public String getUmd_eml() {
        return umd_eml;
    }

    public void setUmd_eml(String umd_eml) {
        this.umd_eml = umd_eml;
    }

    public String getUmd_mob() {
        return umd_mob;
    }

    public void setUmd_mob(String umd_mob) {
        this.umd_mob = umd_mob;
    }

    public String getUmd_mmm() {
        return umd_mmm;
    }

    public void setUmd_mmm(String umd_mmm) {
        this.umd_mmm = umd_mmm;
    }

    public String getUmd_name() {
        return umd_name;
    }

    public void setUmd_name(String umd_name) {
        this.umd_name = umd_name;
    }

    public String getUmd_pos() {
        return umd_pos;
    }

    public void setUmd_pos(String umd_pos) {
        this.umd_pos = umd_pos;
    }

    public String getUmd_prm() {
        return umd_prm;
    }

    public void setUmd_prm(String umd_prm) {
        this.umd_prm = umd_prm;
    }

    public String getUmd_udi() {
        return umd_udi;
    }

    public void setUmd_udi(String umd_udi) {
        this.umd_udi = umd_udi;
    }

    public String getUmd_zone() {
        return umd_zone;
    }

    public void setUmd_zone(String umd_zone) {
        this.umd_zone = umd_zone;
    }

    public String getUmd_category() {
        return umd_category;
    }

    public void setUmd_category(String umd_category) {
        this.umd_category = umd_category;
    }
}
