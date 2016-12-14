package com.kaiback.crossroad.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kaiback.crossroad.R;
import com.kaiback.crossroad.app.MyApplication;
import com.kaiback.crossroad.util.DialogCreator;
import com.kaiback.crossroad.util.HandleResponseCode;
import com.kaiback.crossroad.view.diyview.MenuItemView;
import com.kaiback.crossroad.view.fragment.ConversationListFragment;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.CreateGroupCallback;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by augustinus on 16/12/13.
 */

public class MenuItemController implements View.OnClickListener {
    private MenuItemView mMenuItemView;
    private ConversationListFragment mContext;
    private ConversationListController mController;
    private Dialog mLoadingDialog;
    private Dialog mAddFriendDialog;
    private int mWidth;

    public MenuItemController(MenuItemView view, ConversationListFragment context,
                              ConversationListController controller, int width) {
        this.mMenuItemView = view;
        this.mContext = context;
        this.mController = controller;
        mWidth = width;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_group_ll:
                mContext.dismissPopWindow();
//                mContext.StartCreateGroupActivity();
                mLoadingDialog = DialogCreator.createLoadingDialog(mContext.getActivity(),
                        mContext.getString(R.string.creating_hint));
                mLoadingDialog.show();
                JMessageClient.createGroup("", "", new CreateGroupCallback() {

                    @Override
                    public void gotResult(final int status, String msg, final long groupId) {
                        mLoadingDialog.dismiss();
                        if (status == 0) {
                            Conversation conv = Conversation.createGroupConversation(groupId);
                            mController.getAdapter().setToTop(conv);
                            Intent intent = new Intent();
                            //设置跳转标志
                            intent.putExtra("fromGroup", true);
                            intent.putExtra(MyApplication.MEMBERS_COUNT, 1);
                            intent.putExtra(MyApplication.GROUP_ID, groupId);
                            intent.setClass(mContext.getActivity(), ChatActivity.class);
                            mContext.startActivity(intent);
                        } else {
                            HandleResponseCode.onHandle(mContext.getActivity(), status, false);
                            Log.i("CreateGroupController", "status : " + status);
                        }
                    }
                });
                break;

            // 好友模式添加需要经过验证
            case R.id.add_friend_with_confirm_ll:
                Intent intent = new Intent(mContext.getActivity(), SearchFriendActivity.class);
                mContext.startActivity(intent);
                break;
        }
    }

    private void getUserInfo(final String targetId){
        JMessageClient.getUserInfo(targetId, new GetUserInfoCallback() {
            @Override
            public void gotResult(final int status, String desc, final UserInfo userInfo) {
                mLoadingDialog.dismiss();
                if (status == 0) {
                    Conversation conv = Conversation.createSingleConversation(targetId);
                    if (!TextUtils.isEmpty(userInfo.getAvatar())) {
                        userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                            @Override
                            public void gotResult(int status, String desc, Bitmap bitmap) {
                                if (status == 0) {
                                    mController.getAdapter().notifyDataSetChanged();
                                } else {
                                    HandleResponseCode.onHandle(mContext.getActivity(), status, false);
                                }
                            }
                        });
                    }
                    mController.getAdapter().setToTop(conv);
                    mAddFriendDialog.cancel();
                } else {
                    HandleResponseCode.onHandle(mContext.getActivity(), status, true);
                }
            }
        });
    }

    public void dismissSoftInput() {
        InputMethodManager imm = ((InputMethodManager) mContext.getActivity()
                .getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (mContext.getActivity().getWindow().getAttributes().softInputMode
                != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (mContext.getActivity().getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(mContext.getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private boolean isExistConv(String targetId) {
        Conversation conv = JMessageClient.getSingleConversation(targetId);
        return conv != null;
    }
}
