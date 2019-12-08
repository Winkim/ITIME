package com.winkim.itime.ui.add_timing;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.winkim.itime.R;
import com.winkim.itime.ui.add_timing.AddTimingFragment;

public class AddTimingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timing);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, AddTimingFragment.newInstance())
//                    .commitNow();
//        }
        //实例化fragment_add_timing
        AddTimingFragment fragment = new AddTimingFragment();
        //把fragment_add_timing添加到activity中
        getSupportFragmentManager().beginTransaction().add(R.id.add_timing_container, fragment).commitAllowingStateLoss();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

