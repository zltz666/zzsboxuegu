package com.example.administrator.zzsboxuegu.Activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zzsboxuegu.R;

public class WoActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout mBodyLayout;
    public LinearLayout mBottomLayout;
    private View mCourseBtn;
    private View mEcxercisesBtn;
    private View mMyInfoBtn;
    private TextView tv_course;
    private TextView tv_exercises;
    private TextView tv_myinfo;
    private ImageView iv_course;
    private ImageView iv_exercises;
    private ImageView iv_myinfo;
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        init();
        initBottomBar();
    }

    private void initBottomBar() {
        mBottomLayout = findViewById(R.id.main_bottom_bar);
        mCourseBtn = findViewById(R.id.buttom_bar_course_btn);
        mEcxercisesBtn = findViewById(R.id.buttom_bar_exercises_btn);
        mMyInfoBtn = findViewById(R.id.buttom_bar_myinfo_btn);
        tv_course = findViewById(R.id.butoom_bar_text_course);
        tv_exercises = findViewById(R.id.butoom_bar_text_exercises);
        tv_myinfo = findViewById(R.id.butoom_bar_text_myinfo);
        iv_course = findViewById(R.id.buttom_bar_image_course);
        iv_exercises = findViewById(R.id.buttom_bar_image_exercises);
        iv_myinfo = findViewById(R.id.buttom_bar_image_myinfo);

    }

    private void init() {
        tv_back = findViewById(R.id.tv_back);
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));
        tv_back.setVisibility(View.GONE);
        initBodyLayout();
    }

    private void initBodyLayout() {
        mBodyLayout = findViewById(R.id.main_body);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttom_bar_course_btn:
                clearImage();
                selectView(0);
                break;
            case R.id.buttom_bar_exercises_btn:
                clearImage();
                selectView(1);
                break;
            case R.id.buttom_bar_myinfo_btn:
                clearImage();
                selectView(2);
                break;
            default:
                break;
        }
    }

    private void setListener(){
        for (int i=0;i<mBottomLayout.getChildCount(); i++){
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }
    private void selectView(int index) {
    }

    private void clearImage() {
    }
}
