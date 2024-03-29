package com.winkim.itime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SelectAdapter extends ArrayAdapter<SelectTimingActivity> {
    private int resourceId;
    Calendar calendar = Calendar.getInstance();

    Dialog dialog1 = new Dialog(getContext());
    Dialog dialog2 = new Dialog(getContext());
    int year;
    int month;
    int day;
    int hour;
    int minute;
    int second;
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM dd,yyyy HH:mm", Locale.ENGLISH);

    public SelectAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SelectTimingActivity> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final SelectTimingActivity selectTimingActivity = getItem(position);//获取当前项的实例
        final View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ((ImageView) view.findViewById(R.id.image2)).setImageResource(selectTimingActivity.getPicture());
        ((TextView) view.findViewById(R.id.title2)).setText(selectTimingActivity.getTitle());
        final TextView textViewRemark = view.findViewById(R.id.remark2);
        textViewRemark.setText(selectTimingActivity.getRemark());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    final View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_dialog_date, null);
                    //这个布局在下边,可参考
                    final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);

                    //设置日期简略显示 否则详细显示 包括:星期周
                    datePicker.setCalendarViewShown(true);
                    //初始化当前日期
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    //初始化当前日期
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH), null);

                    //设置date布局
                    builder.setView(view);
                    builder.setTitle("设置日期信息");


                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            year = datePicker.getYear();
                            month = datePicker.getMonth() + 1;
                            day = datePicker.getDayOfMonth();
                            hour = calendar.get(Calendar.HOUR_OF_DAY);
                            minute = calendar.get(Calendar.MINUTE);


                            AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                            final View view2 = LayoutInflater.from(getContext()).inflate(R.layout.activity_dialog_time, null);
                            final TimePicker timePicker = (TimePicker) view2.findViewById(R.id.time_picker);
                            timePicker.setHour(hour);
                            timePicker.setMinute(minute);

                            builder2.setView(view2);
                            builder2.setTitle("设置时间信息");

                            builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    hour = timePicker.getHour();
                                    minute = timePicker.getMinute();

                                    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String datetime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
                                    Date date = new Date();
                                    try {
                                        date = sdFormat.parse(datetime);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    textViewRemark.setText(simpleDateFormat1.format(date));

                                }
                            });
                            builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog2.cancel();
                                }
                            });

                            dialog2 = builder2.create();
                            dialog2.show();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialog1.cancel();
                        }
                    });
                    dialog1 = builder.create();
                    dialog1.show();

                }

                if (position == 1) {
                    final String[] items = {"Week", "Month", "Year", "Custom"};
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(getContext()).setIcon(R.mipmap.ic_launcher)
                            .setTitle("重复设置")
                            .setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    textViewRemark.setText(items[i]);
                                }
                            });
                    builder.create().show();
                }

                if (position == 2) {
                    Intent intentImag = new Intent();
                    intentImag = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intentImag.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    ((Activity) getContext()).startActivityForResult(intentImag, 4);
                }
            }
        });
        return view;
    }




}

