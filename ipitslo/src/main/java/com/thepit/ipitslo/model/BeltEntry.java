package com.thepit.ipitslo.model;

import java.io.Serializable;

/**
 * Created by drice1 on 3/25/15.
 */
public class BeltEntry implements Serializable {

    private String gsxname;
    private String gsxcolor;
    private int gsxprogress;
    private String gsxinfolink;

    public BeltEntry() {
        gsxname = "";
        gsxcolor = "";
        gsxprogress = 0;
        gsxinfolink = "";
    }

    public BeltEntry(String name, String color, int progress, String infolink) {
        this.setColor(color);
        this.setName(name);
        this.setProgress(progress);
        this.setInfolink(infolink);
    }

    public String getName() {
        return gsxname;
    }

    public void setName(String name) {
        this.gsxname = name;
    }

    public String getColor() {
        return gsxcolor;
    }

    public void setColor(String color) {
        this.gsxcolor = color;
    }
    public int getProgress() {
        return gsxprogress;
    }

    public void setProgress(int progress) {
        this.gsxprogress = progress;
    }
    public String getInfolink() {
        return gsxinfolink;
    }
    public void setInfolink(String gsxinfolink) {
        this.gsxinfolink = gsxinfolink;
    }
}


