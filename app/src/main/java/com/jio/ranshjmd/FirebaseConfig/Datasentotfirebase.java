package com.jio.ranshjmd.FirebaseConfig;

public class Datasentotfirebase {
    private String ticketno;
    private String serialno;

    public Datasentotfirebase(String ticketno, String serialno) {
        this.ticketno = ticketno;
        this.serialno = serialno;
    }

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
}
