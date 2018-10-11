package com.datamodel;

public class Person extends AbstractModel {
    private String LastName;
    private String FirstName;
    private String MiddleName;

    public void setLastName(String lastName) { this.LastName = lastName;}
    public String getLastName(){return this.LastName;}

    public void setFirstName(String firstName) { this.FirstName = firstName;}
    public String getFirstName(){return this.FirstName;}

    public void setMiddleName(String middleName) { this.MiddleName = middleName;}
    public String getMiddleName(){return this.MiddleName;}
}
