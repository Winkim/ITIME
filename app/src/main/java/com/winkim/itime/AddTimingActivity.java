package com.winkim.itime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class AddTimingActivity extends AppCompatActivity {
    ListView listView;
    Button btn_Return;
    Button btn_OK;
    EditText editTitle;
    EditText editRemark;
    TextView textView;
    ImageView imageView;
    ImageView img;
    SelectAdapter selectAdapter;
    private ArrayList<SelectTimingActivity> selectTimingActivities;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == Activity.RESULT_OK
                && data != null){
            Toast.makeText(this, "图片已选择", Toast.LENGTH_SHORT).show();
            requestWritePermission();
            Uri selectedImage = data.getData();

            String[] filePathColumns = {MediaStore.Images.Media.DATA};

            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);

            c.moveToFirst();

            int columnIndex = c.getColumnIndex(filePathColumns[0]);

            String imagePath = c.getString(columnIndex);

            showImage(imagePath);

            c.close();

        }
    }

    private void requestWritePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

    }

    private void showImage(String imagePath) {
        Bitmap bm = BitmapFactory.decodeFile(imagePath);

        ((ImageView)findViewById(R.id.select_img)).setImageBitmap(bm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timing);
        btn_Return =findViewById(R.id.button_return);
        btn_OK =findViewById(R.id.button_ok);
        editTitle=findViewById(R.id.edit_text_title);
        editRemark =findViewById(R.id.edit_text_remark);
        listView=findViewById(R.id.list_view_select);
        editTitle=findViewById(R.id.edit_text_title);
        editRemark =findViewById(R.id.edit_text_remark);
        imageView=findViewById(R.id.image2);
        textView=findViewById(R.id.remark2);


        Init();
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String remark=intent.getStringExtra("remark");
        String date=intent.getStringExtra("date");
        final int position=intent.getIntExtra("position",0);
        if(title!=null) {
            editTitle.setText(title);
        }
        if(remark!=null) {
            editRemark.setText(remark);
        }
        if(date!=null) {
            selectTimingActivities.get(0).setRemark(date);
        }

        selectAdapter = new SelectAdapter(AddTimingActivity.this, R.layout.select_item ,selectTimingActivities);
        listView.setAdapter(selectAdapter);

        btn_Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("title", editTitle.getText().toString().trim());
                intent.putExtra("remark", editRemark.getText().toString().trim());
                intent.putExtra("position", position);
                intent.putExtra("date",
                        selectAdapter.year+"-"+selectAdapter.month+"-"+selectAdapter.day+" "
                                +selectAdapter.hour+":"+selectAdapter.minute+":"+selectAdapter.second);
                //intent.putExtra("imagePath", imagePath);
                setResult(RESULT_OK, intent);
                Log.d("timingTitle", editTitle.getText().toString());
                AddTimingActivity.this.finish();
            }
        });
    }


    private void Init()
    {
        selectTimingActivities=new ArrayList<>();
        selectTimingActivities.add(new SelectTimingActivity("日期  ", "长按使用日期计算器",
                R.drawable.ic_alarm_clock_24px));
        selectTimingActivities.add(new SelectTimingActivity("重复设置","无",R.drawable.ic_btn_addtiming_set_repeat_24px));
        selectTimingActivities.add(new SelectTimingActivity("图片 ","",R.drawable.ic_btn_addtiming_set_picture_24px));
        selectTimingActivities.add(new SelectTimingActivity("添加标签 ","",R.drawable.ic_btn_addtiming_set_tag_24px));
    }
}