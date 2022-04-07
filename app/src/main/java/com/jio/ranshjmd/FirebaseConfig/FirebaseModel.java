package com.jio.ranshjmd.FirebaseConfig;



public class FirebaseModel {

    private String versionname;
   private String current_outage;
   private String ticketno;

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public FirebaseModel(String ticketno) {
        this.ticketno = ticketno;
    }

    public FirebaseModel() {
    }

    public String getVersionname() {
        return versionname;
    }

    public String getCurrent_outage() {
        return current_outage;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public void setCurrent_outage(String current_outage) {
        this.current_outage = current_outage;
    }
}
