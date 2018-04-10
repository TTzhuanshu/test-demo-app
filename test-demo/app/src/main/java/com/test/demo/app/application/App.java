package com.test.demo.app.application;

import android.app.Application;

import com.blankj.utilcode.utils.ToastUtils;
import com.blankj.utilcode.utils.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.test.demo.app.network.cookie.CookiesManager;

/**
 * Created by ThinkPad on 2018/4/10.
 */

public class App extends Application {


    private static App mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init() {
        //初始化Leak内存泄露检测工具
        LeakCanary.install(this);
        //Utul库工具初始化
        Utils.init(this);
        ToastUtils.init(false);
        //初始化CookieStore
        CookiesManager.initCookie(getApplicationContext());
    }

    public static App getInstance() {
        return mInstance;
    }


}
