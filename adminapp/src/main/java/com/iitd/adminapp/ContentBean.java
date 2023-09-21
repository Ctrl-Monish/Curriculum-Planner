package com.iitd.adminapp;

public class ContentBean {
    private String heading, duration, resources, instructions, content, activity, klg, assessment;

    public ContentBean(String heading, String duration, String resources, String instructions, String content, String activity, String klg, String assessment) {
        this.heading = heading;
        this.duration = duration;
        this.resources = resources;
        this.instructions = instructions;
        this.content = content;
        this.activity = activity;
        this.klg = klg;
        this.assessment = assessment;
    }



    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getKlg() {
        return klg;
    }

    public void setKlg(String klg) {
        this.klg = klg;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }
}
