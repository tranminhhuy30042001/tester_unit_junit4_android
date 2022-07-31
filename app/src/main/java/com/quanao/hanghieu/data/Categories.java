package com.quanao.hanghieu.data;

import java.util.ArrayList;

public class Categories {
    String name;
    String image;
    ArrayList<Product> arrayList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Product> arrayList) {
        this.arrayList = arrayList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public Categories(String name, ArrayList<Product> arrayList) {
        this.name = name;
        this.arrayList = arrayList;
    }
    public Categories(String name, ArrayList<Product> arrayList, String image) {
        this.name = name;
        this.arrayList = arrayList;
        this.image = image;
    }
}
