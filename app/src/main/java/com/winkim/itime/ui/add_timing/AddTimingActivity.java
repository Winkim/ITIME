package com.winkim.itime.ui.add_timing;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.winkim.itime.R;
import com.winkim.itime.data.model.Timing;
import com.winkim.itime.ui.add_timing.AddTimingFragment;
import com.winkim.itime.ui.timing.TimingSaver;

import java.util.ArrayList;
import java.util.List;

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

    public class TimingsArrayAdapter extends ArrayAdapter<Timing>
    {
        private int resourceId;
        public TimingsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Timing> objects) {
            super(context, resource,objects);
            resourceId=resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater mInflater= LayoutInflater.from(this.getContext());
            View item = mInflater.inflate(this.resourceId,null);

            ImageView img = (ImageView)item.findViewById(R.id.image_view_picture);
            TextView title = (TextView)item.findViewById(R.id.text_view_title);
            TextView date = (TextView)item.findViewById(R.id.text_view_date);
            TextView remark = (TextView)item.findViewById(R.id.text_view_remark);

            Timing time_item= this.getItem(position);
            img.setImageResource(time_item.getPicture());
            title.setText("标题："+time_item.getTitle());
            date.setText("日期"+time_item.getDate());
            remark.setText("备注："+time_item.getRemark());

            return item;
        }
    }

}

