package com.example.administrator.zzsboxuegu.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.zzsboxuegu.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
private TextView tv_version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        init();
    }

    private void init() {
        tv_version=findViewById(R.id.tv_version);
        try {
            PackageInfo info=getPackageManager().getPackageInfo(getPackageName(),0);
            tv_version.setText("v"+info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("v");
        }
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,SplashActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }

        };
        timer.schedule(task,3000);
    }
}
