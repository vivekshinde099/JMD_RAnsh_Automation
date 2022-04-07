package com.jio.ranshjmd.ApiConstructor;

public class StatusPatchNotification {

    private String status_id;

    public StatusPatchNotification(String status_id) {
        this.status_id = status_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }
}
