package com.datamodel;

public class PlaceStorage extends AbstractModel{
    private String placeStorage;

    public PlaceStorage(){}

    public PlaceStorage(String placeStorage){this.placeStorage = placeStorage;}

    public void setPlaceStorage(String placeStorage) { this.placeStorage = placeStorage;}
    public String getPlaceStorage() {return this.placeStorage;}
}
