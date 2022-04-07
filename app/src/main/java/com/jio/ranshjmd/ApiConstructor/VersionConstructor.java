package com.jio.ranshjmd.ApiConstructor;

public class VersionConstructor {
    private  Long ID;
    private  String outage;

    public VersionConstructor (String outage) {
        this.outage = outage;
    }

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

    public String getOutage() { return outage; }

    public void setOutage(String outage) { this.outage = outage; }
}
