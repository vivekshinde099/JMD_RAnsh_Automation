package com.jio.ranshjmd.Models;

public class FeedbackDetails {

    private String Comment;
    private String Email;
    private String Title;
    private String datetime;
    private String Issuetitle;
    private String btnumber;
    private String mobile;

    public FeedbackDetails(String comment, String email, String title, String datetime, String issuetitle, String btnumber, String mobile) {
        Comment = comment;
        Email = email;
        Title = title;
        this.datetime = datetime;
        Issuetitle = issuetitle;
        this.btnumber = btnumber;
        this.mobile = mobile;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getIssuetitle() {
        return Issuetitle;
    }

    public void setIssuetitle(String issuetitle) {
        Issuetitle = issuetitle;
    }

    public String getBtnumber() {
        return btnumber;
    }

    public void setBtnumber(String btnumber) {
        this.btnumber = btnumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
