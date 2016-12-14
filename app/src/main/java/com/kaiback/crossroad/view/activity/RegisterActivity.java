package com.kaiback.crossroad.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kaiback.crossroad.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by augustinus on 16/12/1.
 */

public class RegisterActivity extends Activity {
    @BindView(R.id.input_phone_et)
    EditText inputPhoneEt;
    @BindView(R.id.send_code)
    Button sendCode;
    @BindView(R.id.input_code_et)
    EditText inputCodeEt;
    @BindView(R.id.input_psw_et)
    EditText inputPswEt;
    @BindView(R.id.input_psw_again)
    EditText inputPswAgain;
    @BindView(R.id.owner_radio)
    RadioButton ownerRadio;
    @BindView(R.id.shopkeeper_radio)
    RadioButton shopkeeperRadio;
    @BindView(R.id.input_nick_name)
    EditText inputNickName;
    @BindView(R.id.register)
    Button register;


    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.send_code, R.id.owner_radio, R.id.shopkeeper_radio, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                break;
            case R.id.owner_radio:
                break;
            case R.id.shopkeeper_radio:
                break;
            case R.id.register:
                progressDialog = ProgressDialog.show(RegisterActivity.this,"提示:","正在加载中。。。");
                final String username = inputPhoneEt.getText().toString();
                final String password = inputPswEt.getText().toString();
                //调用极光sdk注册接口
                JMessageClient.register(username, password, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
                        }

                    }
                });

                break;
        }
    }
}
