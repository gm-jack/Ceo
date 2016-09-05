package com.example.yxy.ceo.model.util;

import android.content.Context;
import android.content.res.Configuration;

import com.example.yxy.ceo.model.Model;

import java.util.Locale;

/**
 * Created by yxy on 2016/8/31.
 */

public class LanguageToggleUtils {

    /**
     * 判断系统语言是否是中文语言
     *
     * @param context
     * @return
     */
    public static boolean isZh(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }
    public static void settingLanguage(Context context){
        boolean chinese = Model.getInstance(context).isChinese();
        if(chinese){
            //中文环境下
            Locale.setDefault(Locale.CHINESE);
            Configuration config = context.getResources().getConfiguration();
            config.locale = Locale.CHINESE;
            context.getResources().updateConfiguration(config
                    , context.getResources().getDisplayMetrics());
        }else{
            //英文环境下
            Locale.setDefault(Locale.ENGLISH);
            Configuration config = context.getResources().getConfiguration();
            config.locale = Locale.ENGLISH;
            context.getResources().updateConfiguration(config
                    , context.getResources().getDisplayMetrics());
        }
    }
}
