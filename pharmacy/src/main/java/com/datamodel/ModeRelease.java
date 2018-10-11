package com.datamodel;

public class ModeRelease extends AbstractModel {
    private String title;

    public ModeRelease() {}

    public ModeRelease(String title) {this.title = title;}

    public void setTitle(String title) { this.title = title;}
    public String getTitle() {return this.title;}
}
