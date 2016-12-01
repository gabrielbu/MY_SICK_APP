package com.example.gabrieluliano.helper;
/**
 * Created by gabrieluliano on 24/11/2016.
 */

import java.util.ArrayList;

public class Parent {

    private String name;
    private ArrayList<Child> countryList = new ArrayList<Child>();

    public Parent(String name, ArrayList<Child> countryList) {
        super();
        this.name = name;
        this.countryList = countryList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Child> getCountryList() {
        return countryList;
    }
    public void setCountryList(ArrayList<Child> countryList) {
        this.countryList = countryList;
    };


}