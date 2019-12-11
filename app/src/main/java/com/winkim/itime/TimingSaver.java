package com.winkim.itime;

import android.content.Context;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TimingSaver {
    public TimingSaver(Context context) {
        this.context = context;
    }

    Context context;

    public ArrayList<TimingClass> getTimingClasses() {
        return timingClasses;
    }

    ArrayList<TimingClass> timingClasses = new ArrayList<>();

    public void save(){
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput("TimingClass.txt",Context.MODE_PRIVATE));
            outputStream.writeObject(timingClasses);
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<TimingClass> load(){
        try{
            ObjectInputStream inputStream= new ObjectInputStream(
                    context.openFileInput("TimingClass.txt"));
            timingClasses = (ArrayList<TimingClass>) inputStream.readObject();
            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return timingClasses;
    }
}
