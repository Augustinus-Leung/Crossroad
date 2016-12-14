package com.kaiback.crossroad.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Environment;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.kaiback.crossroad.R;
import com.kaiback.crossroad.app.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by augustinus on 16/12/8.
 */

public class FileHelper {
    private  static FileHelper mInstance = new FileHelper();
    public  static FileHelper getInstance(){
        return mInstance;
    }

    public static boolean isSdCardExist(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String createAvatarPath(String userName){
        String dir = MyApplication.PICTURE_DIR;
        File destDir = new File(dir);
        if (!destDir.exists()){
            destDir.mkdirs();
        }
        File file;
        if (userName!=null){
            file = new File(dir,userName+".png");
        }else {
            file = new File(dir,new DateFormat().format("yyyy_MMdd_hhmmss", Calendar.getInstance(Locale.CHINA))+".png");
        }
        return file.getAbsolutePath();
    }

    public static String getUserAvatarPath(String userName){
        return MyApplication.PICTURE_DIR+userName+".png";
    }

    public interface CopyFileCallback{
        public void copyCallback(Uri uri);
    }

    /*
    复制后裁剪文件
     */
    public void copyAndCrop(final File file,final Activity context,final CopyFileCallback callback){
        if (isSdCardExist()) {
            final Dialog dialog = DialogCreator.createLoadingDialog(context,context.getString(R.string.loading));
            dialog.show();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        String path = createAvatarPath(JMessageClient.getMyInfo().getUserName());
                        final File tempFile = new File(path);
                        FileOutputStream fos = new FileOutputStream(tempFile);
                        byte[] bt = new byte[1024];
                        int c;
                        while((c = fis.read(bt))>0){
                            fos.write(bt,0,c);
                        }
                        //关闭输入、输出流
                        fis.close();
                        fos.close();

                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callback.copyCallback(Uri.fromFile(tempFile));
                            }
                        });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        });
                    }
                }
            });
            thread.start();
        }else {
            ToastUtil.toast(context,context.getString(R.string.sdcard_not_exist_toast));
        }
    }

}
