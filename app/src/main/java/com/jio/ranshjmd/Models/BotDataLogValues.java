package com.jio.ranshjmd.Models;

public class BotDataLogValues {

    private String dateandtime;
    private String type;
    private String operator;
    private String description;

    public BotDataLogValues(String dateandtime, String type, String operator, String description) {
        this.dateandtime = dateandtime;
        this.type = type;
        this.operator = operator;
        this.description = description;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

