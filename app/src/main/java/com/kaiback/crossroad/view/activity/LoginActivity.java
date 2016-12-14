package com.kaiback.crossroad.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaiback.crossroad.R;
import com.kaiback.crossroad.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by augustinus on 16/12/1.
 */

public class LoginActivity extends Activity {
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.phone)
    TextInputLayout phone;
    @BindView(R.id.password)
    TextInputLayout password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.forget_password)
    TextView forgetPassword;

    private int identity;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        identity = getIntent().getIntExtra("identity", 0);
        phone.setHint("请输入手机号码");
        password.setHint("请输入密码");

        initData();

    }

    private void initData(){
        /**=================     获取个人信息不是null则直接进入type界面    =================*/
        UserInfo myInfo = JMessageClient.getMyInfo();
        if (myInfo != null) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @OnClick({R.id.back, R.id.login,R.id.forget_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.login:
                progressDialog = ProgressDialog.show(LoginActivity.this,"提示:","正在加载中");
                final String username = phone.getEditText().getText().toString();
                final String psw = password.getEditText().getText().toString();
                //调用极光sdk的登录界面
                JMessageClient.login(username, psw, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0){
                            progressDialog.dismiss();
                            ToastUtil.toast(LoginActivity.this,"登录成功");
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            progressDialog.dismiss();
                            ToastUtil.toast(LoginActivity.this,"登录失败");
                        }
                    }
                });



                break;
            case R.id.forget_password:
                Intent forgetIntent = new Intent(LoginActivity.this,ForgetPswActivity.class);
                startActivity(forgetIntent);
                break;
        }
    }


}
