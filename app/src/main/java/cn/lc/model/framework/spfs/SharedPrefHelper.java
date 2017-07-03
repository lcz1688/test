package cn.lc.model.framework.spfs;


import android.content.Context;
import android.content.SharedPreferences;

import cn.lc.model.framework.application.SoftApplication;
import cn.lc.model.framework.config.UserInfo;


public class SharedPrefHelper {
    /**
     * SharedPreferences的名字
     */
    private static final String SP_FILE_NAME = "APPLICATION_SP";
    private static SharedPrefHelper sharedPrefHelper = null;
    private static SharedPreferences sharedPreferences;
    /**
     * 经度
     */
    private static final String LONGITUDE = "LONGITUDE";
    /**
     * 纬度
     */
    private static final String LATITUDE = "LATITUDE";
    private static final String USER = "data";
    private static final String HELP = "help";

    public static synchronized SharedPrefHelper getInstance() {
        if (null == sharedPrefHelper) {
            sharedPrefHelper = new SharedPrefHelper();
        }
        return sharedPrefHelper;
    }

    private SharedPrefHelper() {
        sharedPreferences = SoftApplication.softApplication.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setPhoneNumber(String phoneNumber) {
        sharedPreferences.edit().putString("phoneNumber", phoneNumber).commit();
    }

    public String getPhoneNumber() {
        return sharedPreferences.getString("phoneNumber", "");
    }

    public void setPassword(String password) {
        sharedPreferences.edit().putString("password", password).commit();
    }

    public String getPassword() {
        return sharedPreferences.getString("password", "");
    }

    public void setRememberAccount(boolean bool) {
        sharedPreferences.edit().putBoolean("rememberAccount", bool).commit();
    }

    public boolean isRememberAccount() {
        return sharedPreferences.getBoolean("rememberAccount", false);
    }
    /**
     * 是否登录状态
     *
     * @param hasLogin
     */
    public void setHasLogin(boolean hasLogin) {
        sharedPreferences.edit().putBoolean("hasLogin", hasLogin).commit();
    }

    public boolean getHasLogin() {
        return sharedPreferences.getBoolean("hasLogin", false);
    }

    /**
     * 是否第一次
     *
     * @param isFirst
     */
    public void setIsFirst(boolean isFirst) {
        sharedPreferences.edit().putBoolean("isFirst", isFirst).commit();
    }

    public boolean getIsFirst() {
        return sharedPreferences.getBoolean("isFirst", true);
    }

    public void setUserInfo(Object userInfo) {
    }

    public UserInfo getUserInfo() {
        return null;
    }
}
