package com.example.administrator.zzsboxuegu.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zzsboxuegu.Activity.LoginActivity;
import com.example.administrator.zzsboxuegu.R;
import com.example.administrator.zzsboxuegu.Utils.AnalysisUtils;

public class MyInfoView {
    public ImageView iv_head_icon;
    private LinearLayout ll_head;
    private RelativeLayout rl_course_history,rl_setting;
    private TextView tv_user_name;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    public MyInfoView(Activity content){
        mContext=content;
        mInflater=LayoutInflater.from(mContext);
    }
    private void createView(){
        initView();
    }

    private void initView() {
        mCurrentView=mInflater.inflate(R.layout.main_view_myinfo,null);
        ll_head=mCurrentView.findViewById(R.id.ll_head);
        iv_head_icon=mCurrentView.findViewById(R.id.iv_head_icon);
        rl_course_history=mCurrentView.findViewById(R.id.rl_course_history);
        rl_setting=mCurrentView.findViewById(R.id.rl_setting);
        tv_user_name=mCurrentView.findViewById(R.id.tv_user_name);
        mCurrentView.setVisibility(View.VISIBLE);
        setLoginParams(reddLoginStatus());
        ll_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否已经登录
                if (reddLoginStatus()){
                    //已登录跳转到个人资料界面
                }else {
                    //未登录跳转到登录页面
                    Intent intent=new Intent(mContext, LoginActivity.class);
                    mContext.startActivityForResult(intent,1);
                }
            }
        });
        rl_course_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reddLoginStatus()){
                    //跳转到播放记录
                }else {
                    Toast.makeText(mContext,"您还没有登录，请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reddLoginStatus()){
                    //跳转到设置界面
                }else {
                    Toast.makeText(mContext,"您还没有登录，请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean reddLoginStatus() {
        SharedPreferences sp=mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin=sp.getBoolean("isLogin",false);
        return isLogin;
    }

    public void setLoginParams(boolean isLogin) {
        if (isLogin) {
            tv_user_name.setText(AnalysisUtils.readLoginUserName(mContext));
        }else {
            tv_user_name.setText("点击登录");
        }
    }
    public View getView(){
        if (mCurrentView==null){
            createView();
        }
        return mCurrentView;
    }
    public void showView(){
        if (mCurrentView==null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
