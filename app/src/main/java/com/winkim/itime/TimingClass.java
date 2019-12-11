package com.winkim.itime;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TimingClass implements Serializable {
    private String title;
    private String remark;
    private Date date;
    private String repeat;
    private int pictureId;
    private String tag;

    public TimingClass(String title, String remark, Date date, String repeat, Integer pictureId, String tag){
        this.title = title;
        this.remark = remark;
        this.date = date;
        this.repeat = repeat;
        this.pictureId = pictureId;
        this.tag = tag;
    }

    public String getTitle(){ return title; }
    public void setTitle(String title){this.title = title;}

    public Date getDate(){return date;}
    public void setDate(Date date){this.date = date;}

    public String getRemark(){return remark;}
    public void setRemark(String remark){this.remark = remark;}

    public String getRepeat(){ return repeat; }
    public void setRepeat(String repeat){this.repeat = repeat;}

    public int getPicture(){return pictureId;}
    public void setPictureId(int pictureId){this.pictureId = pictureId;}

    private String getTag(){return tag;}
    public void setTag(String tag){this.tag = tag;}

    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, startDate.getHours());
        fromCalendar.set(Calendar.MINUTE, startDate.getMinutes());
        fromCalendar.set(Calendar.SECOND, startDate.getSeconds());
        fromCalendar.set(Calendar.MILLISECOND, 0);
        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, endDate.getHours());
        toCalendar.set(Calendar.MINUTE, endDate.getMinutes());
        toCalendar.set(Calendar.SECOND, endDate.getSeconds());
        toCalendar.set(Calendar.MILLISECOND, 0);
        return (int) ((fromCalendar.getTime().getTime() - toCalendar.getTime().getTime()) / 1000 );
    }
}