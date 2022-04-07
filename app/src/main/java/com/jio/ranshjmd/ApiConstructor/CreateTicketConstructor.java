package com.jio.ranshjmd.ApiConstructor;

public class CreateTicketConstructor {

    private String intid;
    private String rt_agent;
    private String rt_assgroup;
    private String rt_cbt;
    private String rt_opent;
    private String rt_opeup;
    private String rt_parttype;
    private String mob;
    private String rt_prm;
    private String rt_remark;
    private String rt_status;
    private String rt_title;
    private String rt_srval;
    private String attachment;
    private String date_time;
    private String logs;
    private String agent_name;
    private String status;
    private String rt_tflag;

    public CreateTicketConstructor(String rt_agent, String rt_assgroup, String rt_cbt, String rt_opent, String rt_opeup, String rt_parttype, String mob, String rt_prm, String rt_remark, String rt_status, String rt_title, String rt_srval, String rt_tflag) {
        this.rt_agent = rt_agent;
        this.rt_assgroup = rt_assgroup;
        this.rt_cbt = rt_cbt;
        this.rt_opent = rt_opent;
        this.rt_opeup = rt_opeup;
        this.rt_parttype = rt_parttype;
        this.mob = mob;
        this.rt_prm = rt_prm;
        this.rt_remark = rt_remark;
        this.rt_status = rt_status;
        this.rt_title = rt_title;
        this.rt_srval = rt_srval;

        this.rt_tflag = rt_tflag;
    }

    public CreateTicketConstructor(String intid, String attachment, String date_time) {
        this.intid = intid;
        this.attachment = attachment;
        this.date_time = date_time;
    }

    public CreateTicketConstructor(String intid, String date_time, String logs, String agent_name, String status) {
        this.intid = intid;
        this.date_time = date_time;
        this.logs = logs;
        this.agent_name = agent_name;
        this.status = status;
    }


    public String getRt_tflag() {
        return rt_tflag;
    }

    public void setRt_tflag(String rt_tflag) {
        this.rt_tflag = rt_tflag;
    }

    public String getIntid() {
        return intid;
    }

    public void setIntid(String intid) {
        this.intid = intid;
    }

    public String getRt_agent() {
        return rt_agent;
    }

    public void setRt_agent(String rt_agent) {
        this.rt_agent = rt_agent;
    }

    public String getRt_assgroup() {
        return rt_assgroup;
    }

    public void setRt_assgroup(String rt_assgroup) {
        this.rt_assgroup = rt_assgroup;
    }

    public String getRt_cbt() {
        return rt_cbt;
    }

    public void setRt_cbt(String rt_cbt) {
        this.rt_cbt = rt_cbt;
    }

    public String getRt_opent() {
        return rt_opent;
    }

    public void setRt_opent(String rt_opent) {
        this.rt_opent = rt_opent;
    }

    public String getRt_opeup() {
        return rt_opeup;
    }

    public void setRt_opeup(String rt_opeup) {
        this.rt_opeup = rt_opeup;
    }

    public String getRt_parttype() {
        return rt_parttype;
    }

    public void setRt_parttype(String rt_parttype) {
        this.rt_parttype = rt_parttype;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getRt_prm() {
        return rt_prm;
    }

    public void setRt_prm(String rt_prm) {
        this.rt_prm = rt_prm;
    }

    public String getRt_remark() {
        return rt_remark;
    }

    public void setRt_remark(String rt_remark) {
        this.rt_remark = rt_remark;
    }

    public String getRt_status() {
        return rt_status;
    }

    public void setRt_status(String rt_status) {
        this.rt_status = rt_status;
    }

    public String getRt_title() {
        return rt_title;
    }

    public void setRt_title(String rt_title) {
        this.rt_title = rt_title;
    }

    public String getRt_srval() {
        return rt_srval;
    }

    public void setRt_srval(String rt_srval) {
        this.rt_srval = rt_srval;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
