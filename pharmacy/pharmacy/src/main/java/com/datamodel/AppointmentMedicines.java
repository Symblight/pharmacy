package com.datamodel;

public class AppointmentMedicines extends AbstractModel {
    private String group;
    private String description;

    public AppointmentMedicines(){}

    public AppointmentMedicines(String group, String description){
        this.group = group;
        this.description = description;
    }

    public void setGroup(String group) {this.group = group;}
    public String getGroup(){return this.group;}

    public void setDescription(String description) {this.description = description;}
    public String getDescription(){return this.description;}
}
