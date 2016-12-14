package com.kaiback.crossroad.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kaiback.crossroad.R;
import com.kaiback.crossroad.adapter.DividerGridItemDecoration;
import com.kaiback.crossroad.adapter.MyItemDecoration;
import com.kaiback.crossroad.adapter.PersonCenterAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by augustinus on 16/12/2.
 */

public class PersonCenterFragment extends Fragment {
    @BindView(R.id.user_photo)
    ImageView userPhoto;
    @BindView(R.id.qr_code)
    ImageView qrCode;
    @BindView(R.id.person_recyclerview)
    RecyclerView personRecyclerview;

    private List<String> nameList = new ArrayList<>();
    private List<Integer> photoList = new ArrayList<>();
    private PersonCenterAdapter personCenterAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_center, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initData(){
        nameList.clear();
        photoList.clear();

        nameList.add("我的好友");
        nameList.add("信用记录");
        nameList.add("账户设置");
        nameList.add("支付密码");
        nameList.add("提醒设置");
        nameList.add("切换账号");


        photoList.add(R.mipmap.person_center1);
        photoList.add(R.mipmap.person_center2);
        photoList.add(R.mipmap.person_center3);
        photoList.add(R.mipmap.person_center4);
        photoList.add(R.mipmap.person_center5);
        photoList.add(R.mipmap.person_center6);


        personCenterAdapter = new PersonCenterAdapter(getContext(),nameList,photoList);


    }

    private void initView(){

        personRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        personRecyclerview.setAdapter(personCenterAdapter);
        personRecyclerview.addItemDecoration(new DividerGridItemDecoration(getContext()));

    }

    @OnClick({R.id.user_photo, R.id.qr_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_photo:
                break;
            case R.id.qr_code:
                break;
        }
    }
}
