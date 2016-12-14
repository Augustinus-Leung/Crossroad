package com.kaiback.crossroad.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaiback.crossroad.R;
import com.kaiback.crossroad.view.fragment.ConversationListFragment;
import com.kaiback.crossroad.view.fragment.PersonCenterFragment;
import com.kaiback.crossroad.view.fragment.StoreFragment;
import com.kaiback.crossroad.view.fragment.WalletFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by augustinus on 16/12/2.
 */

public class HomeActivity extends FragmentActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.message_img)
    ImageView messageImg;
    @BindView(R.id.message_tv)
    TextView messageTv;
    @BindView(R.id.message_ll)
    LinearLayout messageLl;
    @BindView(R.id.wallet_tv)
    TextView walletTv;
    @BindView(R.id.wallet_img)
    ImageView walletImg;
    @BindView(R.id.wallet_ll)
    LinearLayout walletLl;
    @BindView(R.id.store_img)
    ImageView storeImg;
    @BindView(R.id.store_tv)
    TextView storeTv;
    @BindView(R.id.store_ll)
    LinearLayout storeLl;
    @BindView(R.id.person_img)
    ImageView personImg;
    @BindView(R.id.person_tv)
    TextView personTv;
    @BindView(R.id.person_ll)
    LinearLayout personLl;

    @BindColor(R.color.colorPrimary)
    int selectedColor;
    @BindColor(R.color.unselectedColor)
    int unselectedColor;

    @BindDrawable(R.mipmap.message_place)
    Drawable message_normal;
    @BindDrawable(R.mipmap.message)
    Drawable message_selected;
    @BindDrawable(R.mipmap.wallet_place)
    Drawable wallet_normal;
    @BindDrawable(R.mipmap.wallet)
    Drawable wallet_selected;
    @BindDrawable(R.mipmap.store_place)
    Drawable store_normal;
    @BindDrawable(R.mipmap.store)
    Drawable store_selected;
    @BindDrawable(R.mipmap.person_center_place)
    Drawable person_normal;
    @BindDrawable(R.mipmap.person_center)
    Drawable person_selected;


    private ConversationListFragment messageFragment = new ConversationListFragment();
    private WalletFragment walletFragment = new WalletFragment();
    private StoreFragment storeFragment = new StoreFragment();
    private PersonCenterFragment personCenterFragment = new PersonCenterFragment();
    private FragmentPagerAdapter fragAdapter;
    private List<Fragment> fragList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData(){
        fragList.add(messageFragment);
        fragList.add(walletFragment);
        fragList.add(storeFragment);
        fragList.add(personCenterFragment);
    }

    private void initView(){
        fragAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragList.get(position);
            }

            @Override
            public int getCount() {
                return fragList.size();
            }
        };

        viewpager.setAdapter(fragAdapter);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setOnPageChangeListener(vpSlide);

    }


    public ViewPager.OnPageChangeListener vpSlide = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (viewpager.getCurrentItem() == 0){
                changeSelection(0);
            }else if (viewpager.getCurrentItem() == 1){
                changeSelection(1);
            }else if (viewpager.getCurrentItem() == 2){
                changeSelection(2);
            }else if (viewpager.getCurrentItem() == 3){
                changeSelection(3);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void changeSelection(int selectionIndex){
        switch (selectionIndex){
            case 0:
                messageTv.setTextColor(selectedColor);
                walletTv.setTextColor(unselectedColor);
                storeTv.setTextColor(unselectedColor);
                personTv.setTextColor(unselectedColor);

                messageImg.setImageDrawable(message_selected);
                walletImg.setImageDrawable(wallet_normal);
                storeImg.setImageDrawable(store_normal);
                personImg.setImageDrawable(person_normal);
                break;
            case 1:
                messageTv.setTextColor(unselectedColor);
                walletTv.setTextColor(selectedColor);
                storeTv.setTextColor(unselectedColor);
                personTv.setTextColor(unselectedColor);

                messageImg.setImageDrawable(message_normal);
                walletImg.setImageDrawable(wallet_selected);
                storeImg.setImageDrawable(store_normal);
                personImg.setImageDrawable(person_normal);
                break;
            case 2:
                messageTv.setTextColor(unselectedColor);
                walletTv.setTextColor(unselectedColor);
                storeTv.setTextColor(selectedColor);
                personTv.setTextColor(unselectedColor);

                messageImg.setImageDrawable(message_normal);
                walletImg.setImageDrawable(wallet_normal);
                storeImg.setImageDrawable(store_selected);
                personImg.setImageDrawable(person_normal);
                break;
            case 3:
                messageTv.setTextColor(unselectedColor);
                walletTv.setTextColor(unselectedColor);
                storeTv.setTextColor(unselectedColor);
                personTv.setTextColor(selectedColor);

                messageImg.setImageDrawable(message_normal);
                walletImg.setImageDrawable(wallet_normal);
                storeImg.setImageDrawable(store_normal);
                personImg.setImageDrawable(person_selected);
                break;

        }
    }

    @OnClick({R.id.message_ll, R.id.wallet_ll, R.id.store_ll, R.id.person_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_ll:
                viewpager.setCurrentItem(0);
                changeSelection(0);
                break;
            case R.id.wallet_ll:
                viewpager.setCurrentItem(1);
                changeSelection(1);
                break;
            case R.id.store_ll:
                viewpager.setCurrentItem(2);
                changeSelection(2);
                break;
            case R.id.person_ll:
                viewpager.setCurrentItem(3);
                changeSelection(3);
                break;
        }
    }
}
