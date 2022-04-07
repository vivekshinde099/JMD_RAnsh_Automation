package com.jio.ranshjmd.Models;

public class AutoresolverTickets {
    private String Mobilenuber;
    private String IssuetTitle;
    private String PRM;
    private String Email;
    private String Name;
    private String status;
    private String datetime;
    private String btnum;

    public AutoresolverTickets(String mobilenuber, String issuetTitle, String PRM, String email, String name, String status, long datetime, String btnum) {
        Mobilenuber = mobilenuber;
        IssuetTitle = issuetTitle;
        this.PRM = PRM;
        Email = email;
        Name = name;
        this.status = status;
      //  this.datetime = datetime;
        this.btnum = btnum;
    }

    public String getMobilenuber() {
        return Mobilenuber;
    }

    public void setMobilenuber(String mobilenuber) {
        Mobilenuber = mobilenuber;
    }

    public String getIssuetTitle() {
        return IssuetTitle;
    }

    public void setIssuetTitle(String issuetTitle) {
        IssuetTitle = issuetTitle;
    }

    public String getPRM() {
        return PRM;
    }

    public void setPRM(String PRM) {
        this.PRM = PRM;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getBtnum() {
        return btnum;
    }

    public void setBtnum(String btnum) {
        this.btnum = btnum;
    }
}
