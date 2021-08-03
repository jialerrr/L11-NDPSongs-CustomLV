package com.myapplicationdev.android.p10_ndpsongs_clv;

import java.io.Serializable;

public class Song implements Serializable {

	private int id;
	private String title;
	private String singers;
	private int yearReleased;
	private float stars;

    public Song(String title, String singers, int yearReleased, float stars) {
        this.title = title;
        this.singers = singers;
        this.yearReleased = yearReleased;
        this.stars = stars;
    }

    public Song(int id, String title, String singers, int yearReleased, float stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.yearReleased = yearReleased;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Song setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSingers() {
        return singers;
    }

    public Song setSingers(String singers) {
        this.singers = singers;
        return this;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public Song setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    public float getStars() {
        return stars;
    }

    public Song setStars(float stars) {
        this.stars = stars;
        return this;
    }

//    @Override
//    public String toString() {
//        String starsString = "";
//        if (stars == 5){
//            starsString = "*****";
//        } else if (stars == 4){
//            starsString = "****";
//        }
//
//        //or
//        for(int i = 0; i < stars; i++){
//            starsString += "*";
//        }
//        return title + "\n" + singers + " - " + yearReleased + "\n" + starsString;
//
//    }
}
