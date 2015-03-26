package com.thepit.ipitslo.model;

public class ScheduledClassEntry {



    private int starthour;
    private int startminute;
    private int endhour;
    private int endminute;
    private String classname;

    public ScheduledClassEntry() {
        starthour = 0;
        startminute = 0;
        endhour = 0;
        endminute = 0;
        classname = "";
    }

    public ScheduledClassEntry(int starthour, int startminute, int endhour, int endminute, String classname) {
        this.starthour = starthour;
        this.startminute = startminute;
        this.endhour = endhour;
        this.endminute = endminute;
        this.classname = classname;
    }

    public int getStarthour() {
        return starthour;
    }

    public void setStarthour(int starthour) {
        this.starthour = starthour;
    }

    public int getStartminute() {
        return startminute;
    }

    public void setStartminute(int startminute) {
        this.startminute = startminute;
    }

    public int getEndhour() {
        return endhour;
    }

    public void setEndhour(int endhour) {
        this.endhour = endhour;
    }

    public int getEndminute() {
        return endminute;
    }

    public void setEndminute(int endminute) {
        this.endminute = endminute;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
