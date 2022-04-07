package com.jio.ranshjmd.ApiConstructor;

public class FeedbackDataConstructor {

    private String intid;
    private String ar_fdcomm;
    private String ar_fddt;
    private String ar_fdtit;

    public FeedbackDataConstructor(String ar_fdcomm, String ar_fddt, String ar_fdtit) {
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
