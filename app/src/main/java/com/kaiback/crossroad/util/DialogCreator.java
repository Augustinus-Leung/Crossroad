package com.kaiback.crossroad.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kaiback.crossroad.R;

/**
 * Created by augustinus on 16/12/8.
 */

public class DialogCreator {
    public static Dialog createLoadingDialog(Context context,String msg){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loading,null);
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.jmui_dialog_view);
        ImageView mLoadImg = (ImageView) view.findViewById(R.id.jmui_loading_img);
        TextView mLoadText = (TextView) view.findViewById(R.id.jmui_loading_txt);
        AnimationDrawable mDrawable = (AnimationDrawable) mLoadImg.getDrawable();
        mDrawable.start();
        mLoadText.setText(msg);
        final Dialog loadingDialog = new Dialog(context,R.style.loading_dialog);
        loadingDialog.setCancelable(true);
        loadingDialog.setContentView(layout,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        return loadingDialog;
    }

    public static Dialog createBaseCustomDialog(Context context,String title,String text,View.OnClickListener onClickListener){
        Dialog baseDialog = new Dialog(context,R.style.default_dialog_style);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_base,null);
        baseDialog.setContentView(view);
        TextView titleTv = (TextView) view.findViewById(R.id.dialog_base_title_tv);
        TextView textTv = (TextView) view.findViewById(R.id.dialog_base_text_tv);
        Button confirmBtn = (Button) view.findViewById(R.id.dialog_base_confirm_btn);
        titleTv.setText(title);
        textTv.setText(text);
        confirmBtn.setOnClickListener(onClickListener);
        baseDialog.setCancelable(true);
        return baseDialog;
    }

    public static Dialog createDelConversationDialog(Context context, String title,
                                                     View.OnClickListener listener){
        Dialog dialog = new Dialog(context,R.style.default_dialog_style);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_delete_conv, null);
        dialog.setContentView(v);
        TextView titleTv = (TextView) v.findViewById(R.id.jmui_dialog_title);
        final LinearLayout deleteLl = (LinearLayout) v.findViewById(R.id.jmui_delete_conv_ll);
        titleTv.setText(title);
        deleteLl.setOnClickListener(listener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

}
