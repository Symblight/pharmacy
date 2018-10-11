package com.datamodel;

public class WayApply extends AbstractModel{
    private String title;

    public WayApply(){}

    public WayApply(String title){this.title = title;}

    public void setTitle(String title) { this.title = title;}
    public String getTitle() {return this.title;}
}
