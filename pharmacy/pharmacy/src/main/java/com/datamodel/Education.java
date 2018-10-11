package com.datamodel;

public class Education extends AbstractModel {
    private String modeEducation;

    public Education(){}

    public Education(String modeEducation){ this.modeEducation = modeEducation;}

    public void setModeEducation(String modeEducation) { this.modeEducation = modeEducation;}
    public String getModeEducation(){return this.modeEducation;}
}
