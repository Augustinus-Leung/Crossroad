package com.kaiback.crossroad.view.diyview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kaiback.crossroad.R;


/**
 * Created by augustinus on 16/12/12.
 */

public class ConversationListView {
    private View mConvListFragment;
    private ListView mConvListView = null;
    private TextView mTitle;
    private ImageButton mCreateGroup;
    private LinearLayout mHeader;
    private Context mContext;

    public ConversationListView(View view, Context context) {
        this.mConvListFragment = view;
        this.mContext = context;
    }

    public void initModule(){
        mTitle = (TextView) mConvListFragment.findViewById(R.id.main_title_bar_title);
        mTitle.setText("消息");
        mConvListView = (ListView) mConvListFragment.findViewById(R.id.message_list_view);
        mCreateGroup = (ImageButton) mConvListFragment.findViewById(R.id.create_group_btn);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHeader = (LinearLayout) inflater.inflate(R.layout.message_list_header,mConvListView,false);
        mConvListView.addHeaderView(mHeader);
    }


    public void setConvListAdapter(ListAdapter adapter){
        mConvListView.setAdapter(adapter);
    }

    public void setListener(View.OnClickListener onClickListener){
        mCreateGroup.setOnClickListener(onClickListener);
    }

    public void setItemListeners(AdapterView.OnItemClickListener onClickListener){
        mConvListView.setOnItemClickListener(onClickListener);
    }

    public void setLongClickListener(AdapterView.OnItemLongClickListener listener){
        mConvListView.setOnItemLongClickListener(listener);
    }

    public void showHeaderView() {
        mHeader.findViewById(R.id.network_disconnected_iv).setVisibility(View.VISIBLE);
        mHeader.findViewById(R.id.check_network_hit).setVisibility(View.VISIBLE);
    }

    public void dismissHeaderView() {
        mHeader.findViewById(R.id.network_disconnected_iv).setVisibility(View.GONE);
        mHeader.findViewById(R.id.check_network_hit).setVisibility(View.GONE);
    }




}
