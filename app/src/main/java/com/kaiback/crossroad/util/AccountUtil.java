package com.kaiback.crossroad.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by augustinus on 16/12/3.
 */

public class AccountUtil {

    public static boolean isMobile(String str) {
        Pattern p ;
        Matcher m ;
        boolean b;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 验证号码 手机号 固话均可
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
		/*
		 * 可接受的电话格式有：（手机）
		 */
        String expression = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		/*
		 * 可接受的电话格式有：（座机）
		 */
        String expression2 = "\\d{11}|\\d{12}";
//		CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(phoneNumber);

        Pattern pattern2 = Pattern.compile(expression2);
        Matcher matcher2 = pattern2.matcher(phoneNumber);
        if (matcher.matches() || matcher2.matches()) {
            isValid = true;
        }
        return isValid;
    }


    // 验证手机是否为11位电话数字
    public static boolean accountFormat(String str) {
        Pattern p ;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("[1][358]\\d{9}"); // 验证手机格式
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    // 验证密码是否为6-20位字母数字
    public static boolean passwordFormat(String str) {
        Pattern p ;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("[a-z0-9A-Z]{6,20}"); // 验证密码格式
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag ;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
