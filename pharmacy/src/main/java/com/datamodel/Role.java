package com.datamodel;

public class Role extends AbstractModel {
    private String title;
    private String role;
    private String password;

    public Role(String title, String role, String password){
        this.title = title;
        this.role = role;
        this.password = password;
    }

    public Role(){}

    public void setRole(String role) {this.role = role;}
    public String getRole(){return this.role;}

    public void setPassword(String password) {this.password = password;}
    public String getPassword(){return this.password;}

    public void setTitle(String title) { this.title = title;}
    public String getTitle() {return this.title;}
}
