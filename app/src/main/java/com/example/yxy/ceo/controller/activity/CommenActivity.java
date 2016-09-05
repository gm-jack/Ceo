package com.example.yxy.ceo.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yxy.ceo.R;
import com.example.yxy.ceo.base.ActivityManager;
import com.example.yxy.ceo.base.BaseFragment;

public class CommenActivity extends AppCompatActivity {

    public static String FRAGMENT_NAME = "fragment_name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commen);

        //添加activity管理器
        ActivityManager.getInstance().addActivity(this);

        BaseFragment baseFragment = getFragment();
        addFragment(baseFragment);
    }

    private BaseFragment getFragment() {
        try {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            String string = extras.getString(FRAGMENT_NAME);

            Class<?> aClass = Class.forName(string);
            BaseFragment fragment = (BaseFragment) aClass.newInstance();
            fragment.setArguments(extras);

            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //添加fragment
    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_activity_commen_content, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    //移除fragment
    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        removeFragment();
        super.onDestroy();
    }
}
