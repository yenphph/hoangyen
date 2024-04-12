package com.example.navigation.modal;

public class HomeCategory {
    String name;
    String img_url;
    String type;

    public HomeCategory(String img_url, String name,  String type) {
        this.img_url = img_url;
        this.name = name;
        this.type = type;
    }

    public HomeCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
