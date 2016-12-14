package com.kaiback.crossroad.view.diyview;

import android.view.View;
import android.widget.LinearLayout;

import com.kaiback.crossroad.R;

/**
 * Created by augustinus on 16/12/13.
 */

public class MenuItemView {
    private View mView;
    private LinearLayout mCreateGroupLl;
    private LinearLayout mAddFriendLl;

    public MenuItemView(View view) {
        this.mView = view;
    }

    public void initModule() {
        mCreateGroupLl = (LinearLayout) mView.findViewById(R.id.create_group_ll);
        mAddFriendLl = (LinearLayout) mView.findViewById(R.id.add_friend_with_confirm_ll);
    }

    public void setListeners(View.OnClickListener listener) {
        mCreateGroupLl.setOnClickListener(listener);
        mAddFriendLl.setOnClickListener(listener);
    }

    public void showAddFriendDirect() {
        mAddFriendLl.setVisibility(View.GONE);
    }

    public void showAddFriend() {
        mAddFriendLl.setVisibility(View.VISIBLE);
    }
}
