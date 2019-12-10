package com.winkim.itime.ui.timing;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TimingSaver {
    public TimingSaver(Context context) {
        this.context = context;
    }

    Context context;

    public ArrayList<TimingViewModel> getTimings() {
        return Timings;
    }

    ArrayList<TimingViewModel> Timings = new ArrayList<TimingViewModel>();

    public void save(){
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput("Timing.txt",Context.MODE_PRIVATE));
            outputStream.writeObject(Timings);
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<TimingViewModel> load(){
        try{
            ObjectInputStream inputStream= new ObjectInputStream(
                    context.openFileInput("Timing.txt"));
            Timings = (ArrayList<TimingViewModel>) inputStream.readObject();
            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Timings;
    }
}
