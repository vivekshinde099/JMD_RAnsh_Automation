package com.jio.ranshjmd.ApiConstructor;

public class Attachmentdatapatchconstructor {
    private String intid;
    private String rt_status;
    private String rt_remark;

    public Attachmentdatapatchconstructor(String intid, String rt_status, String rt_remark) {
        this.intid = intid;
        this.rt_status = rt_status;
        this.rt_remark = rt_remark;
    }

    public String getIntid() {
        return intid;
    }

    public void setIntid(String intid) {
        this.intid = intid;
    }

    public String getRt_status() {
        return rt_status;
    }

    public void setRt_status(String rt_status) {
        this.rt_status = rt_status;
    }

    public String getRt_remark() {
        return rt_remark;
    }

    public void setRt_remark(String rt_remark) {
        this.rt_remark = rt_remark;
    }
}
