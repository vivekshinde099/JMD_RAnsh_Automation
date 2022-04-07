package com.jio.ranshjmd.Models;

public class Notificationdata {

    private String Title_format;
    private String Body_format;
    private String Position;

    public Notificationdata(String title_format, String body_format, String position) {
        Title_format = title_format;
        Body_format = body_format;
        Position = position;
    }

    public String getTitle_format() {
        return Title_format;
    }

    public void setTitle_format(String title_format) {
        Title_format = title_format;
    }

    public String getBody_format() {
        return Body_format;
    }

    public void setBody_format(String body_format) {
        Body_format = body_format;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }
}

