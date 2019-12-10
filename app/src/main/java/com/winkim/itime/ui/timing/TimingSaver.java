package com.winkim.itime.ui.timing;

import android.content.Context;

import com.winkim.itime.data.model.Timing;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TimingSaver {
    public TimingSaver(Context context) {
        this.context = context;
    }

    Context context;

    public ArrayList<Timing> getTimings() {
        return Timings;
    }

    ArrayList<Timing> Timings = new ArrayList<>();

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

    public ArrayList<Timing> load(){
        try{
            ObjectInputStream inputStream= new ObjectInputStream(
                    context.openFileInput("Timing.txt"));
            Timings = (ArrayList<Timing>) inputStream.readObject();
            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Timings;
    }
}
