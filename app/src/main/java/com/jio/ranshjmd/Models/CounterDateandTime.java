package com.jio.ranshjmd.Models;

public class CounterDateandTime {
    String DateandTime;
    String IssueTitle;

    public CounterDateandTime(String dateandTime, String issueTitle) {
        DateandTime = dateandTime;
        IssueTitle = issueTitle;
    }

    public String getDateandTime() {
        return DateandTime;
    }

    public void setDateandTime(String dateandTime) {
        DateandTime = dateandTime;
    }

    public String getIssueTitle() {
        return IssueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        IssueTitle = issueTitle;
    }
}
