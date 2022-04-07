package com.jio.ranshjmd.ApiConstructor;

public class TicketListConstructor {
    private String intid;
    private String mob;

    public TicketListConstructor (String intid) {
        this.intid = intid;
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
}
