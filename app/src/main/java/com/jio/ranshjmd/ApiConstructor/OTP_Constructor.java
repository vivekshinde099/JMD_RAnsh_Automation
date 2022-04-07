package com.jio.ranshjmd.ApiConstructor;

public class OTP_Constructor {

    private String id;
    private String s_otp;
    private String v_otp;
    private String mob;
    private String s_dt;
    private String v_dt;

    public OTP_Constructor(String mob) {
        this.mob = mob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS_otp() {
        return s_otp;
    }

    public void setS_otp(String s_otp) {
        this.s_otp = s_otp;
    }

    public String getV_otp() {
        return v_otp;
    }

    public void setV_otp(String v_otp) {
        this.v_otp = v_otp;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getS_dt() {
        return s_dt;
    }

    public void setS_dt(String s_dt) {
        this.s_dt = s_dt;
    }

    public String getV_dt() {
        return v_dt;
    }

    public void setV_dt(String v_dt) {
        this.v_dt = v_dt;
    }
}
