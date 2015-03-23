package com.thepit.ipitslo.model;

public class BlogEntry {
	private String title;
	private String link;

    public BlogEntry() {
        title = "";
        link = "";
    }

	public BlogEntry(String title, String link) {
		this.setLink(link);
		this.setTitle(title);
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
	
}
