package com.kaiback.crossroad.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaiback.crossroad.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by augustinus on 16/11/30.
 */

public class ChooseLoginActivity extends Activity {


    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.shopkeeperLogin)
    LinearLayout shopkeeperLogin;
    @BindView(R.id.ownerLogin)
    LinearLayout ownerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.register, R.id.shopkeeperLogin, R.id.ownerLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                Intent intent = new Intent();
                intent.setClass(ChooseLoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.shopkeeperLogin:
                Intent intent1 = new Intent();
                intent1.setClass(ChooseLoginActivity.this,LoginActivity.class);
                intent1.putExtra("identity",1);
                startActivity(intent1);

                break;
            case R.id.ownerLogin:
                Intent intent2 = new Intent();
                intent2.setClass(ChooseLoginActivity.this,LoginActivity.class);
                intent2.putExtra("identity",0);
                startActivity(intent2);
                break;
        }
    }
}
