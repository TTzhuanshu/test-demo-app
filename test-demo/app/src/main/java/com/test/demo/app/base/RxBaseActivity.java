package com.test.demo.app.base;

import android.os.Bundle;
import android.view.Window;

import com.trello.rxlifecycle.components.RxActivity;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ThinkPad on 2018/4/10.
 */

public abstract class RxBaseActivity extends RxActivity {


    public final static List<RxActivity> mActivities = new LinkedList<RxActivity>();

    public static RxBaseActivity activity;

    private Unbinder bind;

    protected abstract int getLayoutId();

    protected abstract void initViews(Bundle savedInstanceState);


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null)
            bind.unbind();
        synchronized (mActivities){
            mActivities.remove(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        //初始化黄油刀控件绑定框架
        bind = ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
        synchronized (mActivities) {
            mActivities.add(this);
        }

    }

    /**
     * 加载数据
     */
    public void loadData() {
    }

    /**
     * 初始化refreshLayout
     */
    public void initRefreshLayout() {
    }

    /**
     * 初始化recyclerView
     */
    public void initRecyclerView() {
    }

    /**
     * 设置数据显示
     */
    public void finishTask() {
    }


    public void killAll() {
        // 复制了一份mActivities 集合Å
        List<RxActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<>(mActivities);
        }
        for (RxActivity activity : copy) {
            activity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
