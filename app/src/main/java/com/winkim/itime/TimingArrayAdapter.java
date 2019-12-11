package com.winkim.itime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

class TimeAdapter extends ArrayAdapter<TimingClass> {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM  dd,yyyy", Locale.ENGLISH);

    SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("MMM  dd,yyyy HH:mm:ss EEE", Locale.ENGLISH);
    // HH:mm:ss//获取当前时间

    int count;

    public TimeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TimingClass> objects) {
        super(context, resource, objects);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Date date = new Date(System.currentTimeMillis());
        final TimingClass timingClass = getItem(position);//获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_timings, parent, false);
        ((ImageView) view.findViewById(R.id.image_view_picture)).setImageResource(timingClass.getPicture());
        ((TextView) view.findViewById(R.id.text_view_title)).setText(timingClass.getTitle());
        ((TextView) view.findViewById(R.id.text_view_date)).setText((CharSequence) simpleDateFormat.format(timingClass.getDate()));
        ((TextView) view.findViewById(R.id.text_view_remark)).setText(timingClass.getRemark());
        count = timingClass.getGapCount(timingClass.getDate(), date);

        if ((count) >= 0) {
            ((TextView) view.findViewById(R.id.count_text_view)).setText(count/(60*60*24) + " DAYS");
        } else {
            ((TextView) view.findViewById(R.id.count_text_view)).setText(count/(60*60*24)+ " DAYS AGO");
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), TimingChangeActivity.class);
                intent.putExtra("Title", timingClass.getTitle());
                intent.putExtra("Date", simpleDateFormat3.format(timingClass.getDate()));
                intent.putExtra("Remark", timingClass.getRemark());
                intent.putExtra("position", position);
                ((Activity) getContext()).startActivityForResult(intent, 2);
            }
        });

        return view;
    }
}