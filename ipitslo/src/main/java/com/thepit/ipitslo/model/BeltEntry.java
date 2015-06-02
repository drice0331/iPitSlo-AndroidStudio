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

    @Override
    public int hashCode() {
        int hash = 31;
        int result = 17;
        result = hash * result + ((gsxname==null) ? 0 : gsxname.hashCode());
        result = hash * result + ((gsxcolor == null) ? 0 : gsxcolor.hashCode());
        result = hash * result + gsxprogress;
        result = hash * result + ((gsxinfolink == null) ? 0 : gsxinfolink.hashCode());
        return result;
    }


    @Override
    public String toString() {
        return ("gsxname: " + gsxname + " gsxcolor: " + gsxcolor + " gsxprogress: " + gsxprogress +
                " gsxinfolink: " + gsxinfolink);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BeltEntry){
            if (((BeltEntry)o).toString().compareTo(this.toString()) == 0)
                return true;
        }
        return false;
    }
}


