package com.datamodel;

public class Unit extends AbstractModel{
    private String title;

    public Unit() {}

    public Unit(String title) {this.title = title;}

    public void setTitle(String title) { this.title = title;}
    public String getTitle() {return this.title;}
}
