package com.example.yxy.ceo.model;

import android.app.Application;

import com.example.yxy.ceo.model.util.LanguageToggleUtils;
import com.example.yxy.ceo.view.autolayout.config.AutoLayoutConifg;

/**
 * Created by yxy on 2016/8/31.
 */

public class CApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化px适配布局配置
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);
        //读取系统语言设置
        Model.getInstance(this).setChinese(LanguageToggleUtils.isZh(this));
        //捕获异常,配置程序异常退出处理
//        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));
    }
}
