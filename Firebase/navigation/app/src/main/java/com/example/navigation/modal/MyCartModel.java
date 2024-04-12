package com.example.navigation.modal;

import java.io.Serializable;

public class MyCartModel implements Serializable {
    String productName;
    String productPrice;
    String saveCurrenDate;
    String saveCurrentTime;
    int totalPrice;
    String totalQuantity;


    public MyCartModel(String productName, String productPrice, String saveCurrenDate, String saveCurrentTime, int totalPrice, String totalQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.saveCurrenDate = saveCurrenDate;
        this.saveCurrentTime = saveCurrentTime;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }

    public MyCartModel() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getSaveCurrenDate() {
        return saveCurrenDate;
    }

    public void setSaveCurrenDate(String saveCurrenDate) {
        this.saveCurrenDate = saveCurrenDate;
    }

    public String getSaveCurrentTime() {
        return saveCurrentTime;
    }

    public void setSaveCurrentTime(String saveCurrentTime) {
        this.saveCurrentTime = saveCurrentTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
