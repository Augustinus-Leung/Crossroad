package com.kaiback.crossroad.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiback.crossroad.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by augustinus on 16/12/2.
 */

public class PersonCenterAdapter extends RecyclerView.Adapter<PersonCenterAdapter.MyViewHolder> {

    private Context context;
    private List<String> nameList;
    private List<Integer> photoList;

    public PersonCenterAdapter(Context context,List<String> nameLis, List<Integer> photoList){

        this.nameList = nameLis;
        this.photoList = photoList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_person_center, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.personText.setText(nameList.get(position));
        holder.personIcon.setBackgroundResource(photoList.get(position));
    }



    @Override
    public int getItemCount() {
        return nameList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.person_text)
        TextView personText;
        @BindView(R.id.person_icon)
        ImageView personIcon;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
