package com.jio.ranshjmd.Models;

public class Datasent_botdata {
    private String Interaction_ID;
    private String Title;
    private String Status;
    private String Remark;
    private String opentime;
    private String prm;
    private String phonenumber;
    private String assignmentgroup;

    public Datasent_botdata() {
    }

    public String getInteraction_ID() {
        return Interaction_ID;
    }

    public void setInteraction_ID(String interaction_ID) {
        Interaction_ID = interaction_ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getPrm() {
        return prm;
    }

    public void setPrm(String prm) {
        this.prm = prm;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAssignmentgroup() {
        return assignmentgroup;
    }

    public void setAssignmentgroup(String assignmentgroup) {
        this.assignmentgroup = assignmentgroup;
    }

    public Datasent_botdata(String interaction_ID, String title, String status, String remark, String opentime, String prm, String phonenumber, String assignmentgroup) {
        Interaction_ID = interaction_ID;
        Title = title;
        Status = status;
        Remark = remark;
        this.opentime = opentime;
        this.prm = prm;
        this.phonenumber = phonenumber;
        this.assignmentgroup = assignmentgroup;
    }
}
