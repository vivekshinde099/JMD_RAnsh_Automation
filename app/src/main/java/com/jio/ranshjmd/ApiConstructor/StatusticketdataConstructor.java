package com.jio.ranshjmd.ApiConstructor;

public class StatusticketdataConstructor {

    private String id;
    private String intid;
    private String mob;
    private String status;
    private String status_id;

    public StatusticketdataConstructor(String id, String intid, String status, String status_id) {
        this.id = id;
        this.intid = intid;
        this.status = status;
        this.status_id = status_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntid() {
        return intid;
    }

    public void setIntid(String intid) {
        this.intid = intid;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
