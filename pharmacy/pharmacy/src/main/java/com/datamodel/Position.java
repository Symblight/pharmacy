package com.datamodel;

public class Position extends AbstractModel {
    private String position;

    public Position(){}

    public Position(String position){this.position = position;}

    public void setPosition(String position) { this.position = position;}
    public String getPosition() {return this.position;}
}
