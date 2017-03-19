package com.example.gabrieluliano.helper;

/**
 * Created by gabrieluliano on 15/02/2017.
 */

public class Product {
    private String image;
    private String userID;
    private String category;
    private String locationX;
    private String locationY;
    private String title;
    private String brand;
    private String colour;
    private String userName;


    public Product(String image, String userID, String category, String locationX, String locationY, String title, String brand, String colour, String userName) {
        this.image = image;
        this.userID = userID;
        this.category = category;
        this.locationX = locationX;
        this.locationY = locationY;
        this.title = title;
        this.brand = brand;
        this.colour = colour;
        this.userName = userName;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocationX(){
        return locationX;
    }
    public String getLocationY(){
        return locationY;
    }
    public void setLocationX(String locationX){
        this.locationX = locationX;
    }
    public void setLocationY(String locationY){
        this.locationY = locationY;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
