package com.test.demo.app.network.cookie;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * 自动管理Cookies
 * Created by ThinkPad on 2017/12/6.
 */

public class CookiesManager implements CookieJar {

     private static Context mContext;

    public static void initCookie(Context context){
        mContext = context;
    }

    private final PersistentCookieStore cookieStore = new PersistentCookieStore(mContext);

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }
}
