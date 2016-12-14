package com.kaiback.crossroad.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.kaiback.crossroad.R;
import com.kaiback.crossroad.app.BaseActivity;
import com.kaiback.crossroad.app.MyApplication;
import com.kaiback.crossroad.controller.CreateGroupController;
import com.kaiback.crossroad.view.diyview.CreateGroupView;

/**
 * Created by augustinus on 16/12/14.
 */
/*
创建群聊
 */
public class CreateGroupActivity extends BaseActivity {
    private CreateGroupView mCreateGroupView;
    private CreateGroupController mCreateGroupController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        mCreateGroupView = (CreateGroupView) findViewById(R.id.create_group_view);
        mCreateGroupView.initModule();
        mCreateGroupController = new CreateGroupController(mCreateGroupView, this);
        mCreateGroupView.setListeners(mCreateGroupController);
    }


    public void startChatActivity(long groupId, String groupName) {
        Intent intent = new Intent();
        //设置跳转标志
        intent.putExtra("fromGroup", true);
        intent.putExtra(MyApplication.GROUP_ID, groupId);
        intent.putExtra(MyApplication.GROUP_NAME, groupName);
        intent.putExtra(MyApplication.MEMBERS_COUNT, 1);
        intent.setClass(this, ChatActivity.class);
        startActivity(intent);
        finish();
    }
}
