package com.winkim.itime;

import java.util.Date;

public class SelectTimingActivity {
    private String title;
    private String remark;
    private Date date;
    private String repeat;
    private int pictureId;
    private String tag;

    public SelectTimingActivity(String title, String remark, Integer pictureId){
        this.title = title;
        this.remark = remark;
//        this.date = date;
//        this.repeat = repeat;
        this.pictureId = pictureId;
//        this.tag = tag;
    }

    public String getTitle(){ return title; }
//    public void setTitle(String title){this.title = title;}
//
//    public Date getDate(){return date;}
//    public void setDate(Date date){this.date = date;}

    public String getRemark(){return remark;}
    public void setRemark(String remark){this.remark = remark;}

//    public String getRepeat(){ return repeat; }
//    public void setRepeat(String repeat){this.repeat = repeat;}

    public int getPicture(){return pictureId;}
//    public void setPictureId(int pictureId){this.pictureId = pictureId;}
//
//    private String getTag(){return tag;}
//    public void setTag(String tag){this.tag = tag;}

}
