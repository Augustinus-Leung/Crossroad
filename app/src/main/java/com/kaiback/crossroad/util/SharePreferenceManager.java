package com.kaiback.crossroad.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by augustinus on 16/12/10.
 */

public class SharePreferenceManager {
    static SharedPreferences sp;

    public static void init(Context context,String name){
        sp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    private static final String KEY_CACHED_USERNAME = "cached_username";

    public static void setCachedUsername(String username){
        if (null != sp){
            sp.edit().putString(KEY_CACHED_USERNAME,username).apply();
        }
    }
    public static String getCachedUsername(){
        if (null!=sp){
            return sp.getString(KEY_CACHED_USERNAME,null);
        }
        return null;
    }

    public static final String KEY_CACHED_AVATAR_PATH = "cached_avatar_path";
    public static void setCachedAvatarPath(String path){
        if (null!=sp){
            sp.edit().putString(KEY_CACHED_AVATAR_PATH,path).apply();
        }
    }
    public static String getCachedAvatarPath() {
        if (null != sp) {
            return sp.getString(KEY_CACHED_AVATAR_PATH, null);
        }
        return null;
    }


    private static final String CACHED_SHOW_CONTACT = "CachedShowContact";

    public static void setCachedShowContact(boolean flag) {
        if (null != sp) {
            sp.edit().putBoolean(CACHED_SHOW_CONTACT, flag).apply();
        }
    }

    public static boolean getCachedShowContact() {
        return null != sp && sp.getBoolean(CACHED_SHOW_CONTACT, false);
    }
}
