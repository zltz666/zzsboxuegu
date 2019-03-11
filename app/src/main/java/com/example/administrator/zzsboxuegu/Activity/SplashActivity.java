package com.example.administrator.zzsboxuegu.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zzsboxuegu.R;
import com.example.administrator.zzsboxuegu.Utils.MD5Utils;

public class SplashActivity extends AppCompatActivity {
    private TextView tv_main_title;   //标题
    private TextView tv_back;          //返回按钮
    private Button btn_register;      //注册按钮
    private EditText et_user_name,et_psw,et_psw_again;
    private String username,psw,pswAgain;
    private RelativeLayout rl_title_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //改为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        init();
    }

    private void init() {
        tv_main_title=findViewById(R.id.tv_main_title);
        tv_main_title.setText("注册");
        tv_back=findViewById(R.id.tv_back);
        rl_title_bar=findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        btn_register=findViewById(R.id.btn_register);
        et_user_name=findViewById(R.id.et_user_name);
        et_psw=findViewById(R.id.et_psw);
        et_psw_again=findViewById(R.id.et_psw_again);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SplashActivity.this.finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(SplashActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(psw)){
                    Toast.makeText(SplashActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(SplashActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if (!psw.equals(pswAgain)){
                    Toast.makeText(SplashActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }else if (isExistUsername(username)){
                    Toast.makeText(SplashActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(SplashActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    saveRegiedInfo(username,psw);
                    Intent intent=new Intent();
                    intent.putExtra("username",username);
                    setResult(RESULT_OK,intent);
                    SplashActivity.this.finish();
                }
            }
        });
    }

    public void getEditString() {
        username=et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        pswAgain=et_psw_again.getText().toString().trim();

    }
    private boolean isExistUsername(String username) {
        boolean has_username = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(username, "");
        if (!TextUtils.isEmpty(spPsw)) {
            has_username = true;
        }
        return has_username;
    }
    private void saveRegiedInfo(String username,String psw){
        String md5psw= MD5Utils.md5(psw);
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(username,md5psw);
        editor.commit();
    }
}
