package com.winkim.itime.data.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

public class Timing implements Serializable {
    private String title;
    private String remark;
    private String date;
    private ArrayList<Integer> repeats;
    private Integer picture;
    private String tag;

    public Timing(String title,String remark,String date,ArrayList<Integer> repeats,Integer picture,String tag){
        this.title = title;
        this.remark = remark;
        this.date = date;
        this.repeats = repeats;
        this.picture = picture;
        this.tag = tag;
    }

    public String getTitle(){ return title; }

    public String getDate(){return date;}

    public String getRemark(){return remark;}

    public ArrayList<Integer> getRepeats(){ return repeats; }

    public int getPicture(){return picture;}

    private String getTag(){return tag;}
}