package com.datamodel;

public class Pharmacy extends AbstractModel {
    private String title;
    private String address;
    private String numberPhone;
    private String license;

    public Pharmacy(){}

    public Pharmacy(String title, String address, String numberPhone, String license) {
        this.title = title;
        this.address = address;
        this.numberPhone = numberPhone;
        this.license = license;
    }

    public void setTitle(String title) { this.title = title;}
    public String getTitle() {return this.title;}

    public void setAddress(String address) { this.address = address;}
    public String getAddress() {return this.address;}

    public void setNumberPhone(String numberPhone) { this.numberPhone = numberPhone;}
    public String getNumberPhone() {return this.numberPhone;}

    public void setLicense(String license) { this.license = license;}
    public String getLicense() {return this.license;}
}
