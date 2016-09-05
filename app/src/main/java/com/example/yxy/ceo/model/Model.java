package com.example.yxy.ceo.model;

import android.content.Context;

import com.example.yxy.ceo.model.net.HttpUtils;
import com.example.yxy.ceo.model.util.LanguageToggleUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yxy on 2016/8/31.
 */

public class Model {
    public volatile static Model mModel = null;
    //系统语言是否是中文
    private boolean isChinese = true;
    //用户是否登录
    private boolean isLogin = false;
    //全局线程池
    private ExecutorService mExecutorService;
    //OkHttp
    private HttpUtils mHttpUtils;

    private Context mContext;

    public static Model getInstance(Context context) {
        Model model = mModel;
        if (mModel == null) {
            mModel = model;
            synchronized (Model.class) {
                if (model == null) {
                    model = new Model(context);
                    mModel = model;
                }
            }
        }
        return model;
    }

    public Model(Context context) {
        mContext = context;
        mExecutorService = Executors.newCachedThreadPool();
        mHttpUtils = new HttpUtils(context);
    }

    public boolean isChinese() {
        return isChinese;
    }

    public void setChinese(boolean chinese) {
        isChinese = chinese;
        LanguageToggleUtils.settingLanguage(mContext);
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public ExecutorService getExecutorService() {
        return mExecutorService;
    }

    public HttpUtils getHttp() {
        return mHttpUtils;
    }
}
