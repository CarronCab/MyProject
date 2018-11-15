package com.example.charl.myproject;

import com.google.gson.annotations.SerializedName;

public class Anime {

/*    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Anime(String name) {
        this.name = name;
    }*/

   //@SerializedName("amiiboSeries")
    private String name;

    public Anime(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
