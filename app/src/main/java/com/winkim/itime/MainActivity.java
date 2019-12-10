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
import com.winkim.itime.data.model.Timing;
import com.winkim.itime.ui.add_timing.AddTimingActivity;
import com.winkim.itime.ui.timing.TimingSaver;
import com.winkim.itime.ui.timing.TimingViewModel;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ArrayList<Timing> timingArrayList = new ArrayList<>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormatMore = new SimpleDateFormat("MMM  dd,yyyy HH:mm:ss EEE", Locale.ENGLISH);

    TimingSaver timingSaver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddTimingActivity.class);
                startActivity(intent);
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

    @Override
    protected void onDestory(){
        super.onDestroy();
        timingSaver.save();
    }

    @Override
    private void init(){
        timingSaver = new TimingSaver(this);
        timingArrayList = timingSaver.load();
        if(timingArrayList.size()==0){
            try {
                timingArrayList.add(
                        new Timing("New Year", "111",simpleDateFormat.parse("2020-01-01") ,
                                "每年",R.drawable.ic_timing_picture_init,"11"))
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
                    String returnedTitle = data.getStringExtra("title");
                    String returnedRemark = data.getStringExtra("remake");
                    String returnedDate=data.getStringExtra("date");

                    //Bitmap bm = BitmapFactory.decodeFile(returnedImage);

                    SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    try {
                        date = sdFormat.parse(returnedDate);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    getTimingArrayList().add(0,new Timing(returnedTitle, returnedRemark,returnedDate,
                            R.drawable.ic_timing_picture_init));
                    AddTimingActivity.TimingsArrayAdapter.notifyDataSetChanged();
                }
                /*if (data!=null){
                    Uri selectedImage = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    String returnedImage=data.getStringExtra("ImagePath");
                    imagePath = c.getString(columnIndex);
                    Log.d("imagepath", returnedImage);
                }*/
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    int position=data.getIntExtra("position", 0);
                    timeItemList.remove(position);
                    timeAdapter.notifyDataSetChanged();
                }
                if (resultCode == RESULT_UPDATE) {
                    int position = data.getIntExtra("position", 0);
                    String title = data.getStringExtra("title");
                    String description = data.getStringExtra("description");
                    String date=data.getStringExtra("date");
                    TimeItem timeItem=timeItemList.get(position);
                    timeItem.setTitle(title);
                    timeItem.setDescription(description);
                    try {
                        timeItem.setDate(simpleDateFormat.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    timeAdapter.notifyDataSetChanged();
                }
                break;



            default:
        }

    }

    public ArrayList<Timing> getTimingArrayList(){return timingArrayList;}

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
