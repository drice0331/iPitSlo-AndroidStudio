package com.thepit.ipitslo.model;

import java.io.Serializable;

/**
 * Created by drice1 on 3/25/15.
 */
public class BeltEntry implements Serializable {

    private String name;
    private String color;
    private int progress;

    public BeltEntry() {
        name = "";
        color = "";
        progress = 0;
    }

    public BeltEntry(String name, String color, int progress) {
        this.setColor(color);
        this.setName(name);
        this.setProgress(progress);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}


