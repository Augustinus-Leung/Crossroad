package com.kaiback.crossroad.controller;

import android.app.Dialog;
import android.util.Log;
import android.view.View;

import com.kaiback.crossroad.R;
import com.kaiback.crossroad.util.DialogCreator;
import com.kaiback.crossroad.util.HandleResponseCode;
import com.kaiback.crossroad.view.activity.CreateGroupActivity;
import com.kaiback.crossroad.view.diyview.CreateGroupView;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.CreateGroupCallback;

/**
 * Created by augustinus on 16/12/14.
 */

public class CreateGroupController implements View.OnClickListener {
    private CreateGroupView mCreateGroupView;
    private CreateGroupActivity mContext;
    private Dialog mDialog = null;
    private String mGroupName;

    public CreateGroupController(CreateGroupView view, CreateGroupActivity context) {
        this.mCreateGroupView = view;
        this.mContext = context;
        initData();
    }

    private void initData() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.creat_group_return_btn:
                mContext.finish();
                break;
            case R.id.jmui_commit_btn:
                mGroupName = mCreateGroupView.getGroupName();
                if (mGroupName.equals("")) {
                    mCreateGroupView.groupNameError(mContext);
                    return;
                }
                mDialog = DialogCreator.createLoadingDialog(mContext, mContext.getString(R.string.creating_hint));
                final String desc = "";
                mDialog.show();
                JMessageClient.createGroup(mGroupName, desc, new CreateGroupCallback() {

                    @Override
                    public void gotResult(final int status, String msg, final long groupId) {
                        mDialog.dismiss();
                        if (status == 0) {
                            mContext.startChatActivity(groupId, mGroupName);
                        } else {
                            HandleResponseCode.onHandle(mContext, status, false);
                            Log.i("CreateGroupController", "status : " + status);
                        }
                    }
                });
                break;

        }
    }
}
