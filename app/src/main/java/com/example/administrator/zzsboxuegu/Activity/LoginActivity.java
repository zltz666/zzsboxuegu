package com.example.administrator.zzsboxuegu.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zzsboxuegu.R;
import com.example.administrator.zzsboxuegu.Utils.MD5Utils;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_main_title; //标题
    private TextView tv_back,tv_register,tv_find_psw;
    private Button btn_login;
    private String username,psw,spPsw;
    private EditText et_user_name,et_psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        init();
    }

    private void init() {
        tv_main_title=findViewById(R.id.tv_main_title);
        tv_main_title.setText("登录");
        tv_back=findViewById(R.id.tv_back);
        tv_register=findViewById(R.id.tv_register);
        tv_find_psw=findViewById(R.id.tv_find_psw);
        btn_login=findViewById(R.id.btn_login);
        et_user_name=findViewById(R.id.et_user_name);
        et_psw=findViewById(R.id.et_psw);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SplashActivity.class);
                startActivityForResult(intent,1);
            }
        });
        tv_find_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=et_user_name.getText().toString().trim();
                psw=et_psw.getText().toString().trim();
                String md5Psw= MD5Utils.md5(psw);
                spPsw=readPsw(username);
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(LoginActivity.this,"请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(psw)){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if (md5Psw.equals(spPsw)){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    saveLoginStatus(true,username);
                    Intent intent=new Intent();
                    intent.putExtra("isLogin",true);
                    setResult(RESULT_OK,intent);
                    LoginActivity.this.finish();
                    return;
                }else if (!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw)){
                    Toast.makeText(LoginActivity.this,"输入的用户名或密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(LoginActivity.this,"用户名不存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private String readPsw(String username){
        SharedPreferences sp=getSharedPreferences("logininInfo",MODE_PRIVATE);
        return sp.getString(username,"");
    }
    private void saveLoginStatus(boolean status,String username){
        SharedPreferences sp=getSharedPreferences("logininInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin",status);
        editor.putString("loginUserName",username);
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            String username=data.getStringExtra("username");
            if (!TextUtils.isEmpty(username)){
                et_user_name.setText(username);
                et_user_name.setSelection(username.length());
            }
        }
    }
}
