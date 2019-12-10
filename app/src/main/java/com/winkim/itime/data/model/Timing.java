package com.winkim.itime.data.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Timing implements Serializable {
    private String title;
    private String remark;
    private Date date;
    private String repeat;
    private int pictureId;
    private String tag;

    public Timing(String title,String remark,Date date,String repeat,Integer pictureId,String tag){
        this.title = title;
        this.remark = remark;
        this.date = date;
        this.repeat = repeat;
        this.pictureId = pictureId;
        this.tag = tag;
    }

    public String getTitle(){ return title; }

    public Date getDate(){return date;}

    public String getRemark(){return remark;}

    public String getRepeat(){ return repeat; }

    public int getPicture(){return pictureId;}

    private String getTag(){return tag;}
}