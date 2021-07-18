package com.example.fooddeliverybyesya.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    @SerializedName("categories")
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
