package com.example.gabrieluliano.my_sick_app;

/**
 * Created by gabrieluliano on 15/02/2017.
 */

public class Product {
    private String image;
    private String userID;
    private String category;
    private String locationX;
    private String locationY;

    public Product(String image, String userID, String category, String locationX, String locationY) {
        this.image = image;
        this.userID = userID;
        this.category = category;
        this.locationX = locationX;
        this.locationY = locationY;
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

}
