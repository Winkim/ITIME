package com.winkim.itime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import static com.winkim.itime.MainActivity.RESULT_UPDATE;

public class TimingChangeActivity extends AppCompatActivity {
    Button btn_Return;
    Button btn_Delete;
    Button btn_Share;
    Button btn_Update;
    Context context=this;
    TextView titleTextView2,remarkTextView2;
    TextView dateTextView,countTextView;
    private MyCount mc1,mc2;
    private int position;
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMM  dd,yyyy HH:mm:ss EEE", Locale.ENGLISH);
    SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int count;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 3:
                if (resultCode == RESULT_OK) {
                    int position = data.getIntExtra("position", 0);
                    String title = data.getStringExtra("title");
                    String remark = data.getStringExtra("remake");
                    String date=data.getStringExtra("date");
                    String repeat = data.getStringExtra("repeat");
                    String tag=data.getStringExtra("tag");
                    Date date3 = new Date();
                    Date date2=new Date(System.currentTimeMillis());
                    try {
                        date3=sdFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    titleTextView2.setText(title);
                    remarkTextView2.setText(remark);
                    dateTextView.setText(simpleDateFormat2.format(date3));

                    int count2=0;
                    count2 = TimingClass.getGapCount(date3, date2);
                    mc1.cancel();
                    mc2 = new MyCount(count2*1000, 1000);
                    mc2.start();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing_change);

        btn_Return=findViewById(R.id.button_return);
        btn_Delete=findViewById(R.id.button_delete);
        btn_Share=findViewById(R.id.button_share);
        btn_Update=findViewById(R.id.button_update);
        titleTextView2=findViewById(R.id.title_text_view2);
        remarkTextView2=findViewById(R.id.remark_text_view2);
        dateTextView=findViewById(R.id.data_text_view);
        countTextView=findViewById(R.id.count_text_view);

        final Intent intent=getIntent();
        final String title=intent.getStringExtra("Title");
        String date=intent.getStringExtra("Date");
        final String remark=intent.getStringExtra("Remark");
        position=intent.getIntExtra("position", 0);
        Date date1=new Date(System.currentTimeMillis());
        int count=0;
        try {
            count = TimingClass.getGapCount(simpleDateFormat2.parse(date), date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        titleTextView2.setText(title);
        remarkTextView2.setText(remark);
        dateTextView.setText(date);

        mc1 = new MyCount(count*1000, 1000);

        mc1.start();
        btn_Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent();
                intent.putExtra("title", titleTextView2.getText().toString().trim());
                intent.putExtra("Remark", remarkTextView2.getText().toString().trim());
                intent.putExtra("position", position);
                intent.putExtra("date", dateTextView.getText().toString().trim());
                setResult(RESULT_UPDATE, intent);
                finish();
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(context)
                        .setMessage("确定删除这个倒计时吗？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent1=new Intent();
                                intent1.putExtra("position", position);
                                setResult(RESULT_OK, intent1);
                                TimingChangeActivity.this.finish();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(TimingChangeActivity.this,AddTimingActivity.class);
                intent2.putExtra("title", title);
                intent2.putExtra("Remark", remark);
                intent2.putExtra("date",dateTextView.getText().toString() );
                startActivityForResult(intent2,3);
            }
        });


    }

    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override

        public void onFinish() {
            countTextView.setText("finish");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            long days=millisUntilFinished / (1000 * 60 * 60 * 24 );
            long hours=millisUntilFinished / (1000*60*60) -days*24;
            long minutes=millisUntilFinished / (1000*60)-days*24*60-hours*60;
            long seconds=millisUntilFinished / (1000)-days*24*60*60-hours*60*60-minutes*60;
            countTextView.setText( days+ " DAYS "+
                    +hours + " HRS "+minutes+" MNIS "+seconds+" SECS");

        }
    }

}
