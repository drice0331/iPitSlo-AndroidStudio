package com.thepit.ipitslo.model;

/**
 * Created by drice1 on 3/25/15.
 */
public class YoutubeEntry {

    private String title;
    private String link;
    private String thumbnail;

    public YoutubeEntry() {
        title = "";
        link = "";
        thumbnail = "";
    }

    public YoutubeEntry(String title, String link, String thumbnail) {
        this.setLink(link);
        this.setTitle(title);
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

