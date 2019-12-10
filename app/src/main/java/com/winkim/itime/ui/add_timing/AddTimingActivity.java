package com.winkim.itime.ui.add_timing;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.winkim.itime.R;
import com.winkim.itime.data.model.Timing;
import com.winkim.itime.ui.add_timing.AddTimingFragment;
import com.winkim.itime.ui.timing.TimingSaver;

import java.util.ArrayList;

public class AddTimingActivity extends AppCompatActivity {

    private TimingSaver timingSaver;
    private ArrayList<TimingSaver> timingSavers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timing);

        timingSaver = new TimingSaver(this);



        //实例化fragment_add_timing
        AddTimingFragment fragment = new AddTimingFragment();
        //把fragment_add_timing添加到activity中
        getSupportFragmentManager().beginTransaction().add(R.id.add_timing_container, fragment).commitAllowingStateLoss();

        //给界面左上角添加返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    //给界面左上角添加返回键
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    protected class TimingsArrayAdapter extends ArrayAdapter<Timing>
//    {
//        private
//    }

}

