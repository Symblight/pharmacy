package com.datamodel;

public class Supplier extends Person {
    private String title;
    private String agent;
    private String position; // должность
    private String address;
    private String numberPhone;
    private String email;

    public Supplier(){}

    public Supplier(String title, String agent, String position, String address, String numberPhone, String email) {
        this.title = title;
        this.agent = agent;
        this.position = position;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
    }

    public void setTitle(String title) { this.title = title;}
    public String getTitle() {return this.title;}

    public void setAgent(String agent) { this.agent = agent;}
    public String getAgent() {return this.agent;}

    public void setPosition(String position) { this.position = position;}
    public String getPosition() {return this.position;}

    public void setAddress(String address) { this.address = address;}
    public String getAddress() {return this.address;}

    public void setNumberPhone(String numberPhone) { this.numberPhone = numberPhone;}
    public String getNumberPhone() {return this.numberPhone;}

    public void setEmail(String email) { this.email = email;}
    public String getEmail() {return this.email;}

}
