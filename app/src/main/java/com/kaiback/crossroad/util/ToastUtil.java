package com.kaiback.crossroad.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by augustinus on 16/12/3.
 */

public class ToastUtil {
    public static void toast(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }
}
