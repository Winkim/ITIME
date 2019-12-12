package com.winkim.itime;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_UPDATE = 901;
    private AppBarConfiguration mAppBarConfiguration;
    private ArrayList<TimingClass> timingClassArrayList = new ArrayList<>();
    TimingArrayAdapter timingsArrayAdapter;
    ListView listView;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormatMore = new SimpleDateFormat("MMM  dd,yyyy HH:mm:ss EEE", Locale.ENGLISH);

    TimingSaver timingSaver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        listView=findViewById(R.id.list_view_content);

        setSupportActionBar(toolbar);

        init();

        timingsArrayAdapter = new TimingArrayAdapter(MainActivity.this,R.layout.list_item_timings, timingClassArrayList);
        listView.setAdapter(timingsArrayAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add new timing", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddTimingActivity.class);
                startActivityForResult(intent,1);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_timing, R.id.nav_label, R.id.nav_change_font,
                R.id.nav_theme_color, R.id.nav_advanced_version,R.id.nav_setting, R.id.nav_about,R.id.nav_help_and_feedback)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    //@Override
    protected void onDestory(){
        super.onDestroy();
        timingSaver.save();
    }

    //@Override
    private void init(){
        timingSaver = new TimingSaver(this);
        timingClassArrayList = timingSaver.load();
        if(timingClassArrayList.size()==0){
            try {
                timingClassArrayList.add(
                        new TimingClass("New Year", "111",simpleDateFormat.parse("2020-01-01") ,
                                "每年",R.drawable.ic_timing_picture_init,"11"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    int position=data.getIntExtra("position",0);
                    String returnedTitle = data.getStringExtra("title");
                    String returnedRemark = data.getStringExtra("remake");
                    String returnedDate=data.getStringExtra("date");
                    String returnedRepeat = data.getStringExtra("repeat");
                    String returnedTag=data.getStringExtra("tag");

                    //Bitmap bm = BitmapFactory.decodeFile(returnedImage);

                    SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    try {
                        date = sdFormat.parse(returnedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    timingClassArrayList.add(position,new TimingClass(returnedTitle, returnedRemark,date,returnedRepeat,
                            R.drawable.ic_timing_picture_init,returnedTag));
                    timingsArrayAdapter.notifyDataSetChanged();

                    Toast.makeText(this, "新建成功", Toast.LENGTH_SHORT).show();
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    int position=data.getIntExtra("position", 0);
                    timingClassArrayList.remove(position);
                    timingsArrayAdapter.notifyDataSetChanged();
                }
                if (resultCode == RESULT_UPDATE) {
                    int position = data.getIntExtra("position", 0);
                    String title = data.getStringExtra("title");
                    String remark = data.getStringExtra("remake");
                    String date=data.getStringExtra("date");
                    String repeat = data.getStringExtra("repeat");
                    String tag=data.getStringExtra("tag");
                    TimingClass timingClass = timingClassArrayList.get(position);
                    timingClass.setTitle(title);
                    timingClass.setRemark(remark);
                    timingClass.setRepeat(repeat);
                    timingClass.setTag(tag);

                    try {
                        timingClass.setDate(simpleDateFormatMore.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    timingsArrayAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
