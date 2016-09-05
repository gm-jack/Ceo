package com.example.yxy.ceo.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yxy.ceo.R;
import com.example.yxy.ceo.base.ActivityManager;
import com.example.yxy.ceo.controller.adapter.MenuMainAdapter;
import com.example.yxy.ceo.controller.fragment.AgenderMeetingFragment;
import com.example.yxy.ceo.controller.fragment.AirportStyleFragment;
import com.example.yxy.ceo.controller.fragment.BusinessServiceFragment;
import com.example.yxy.ceo.controller.fragment.DataDownloadFragment;
import com.example.yxy.ceo.controller.fragment.FaqFragment;
import com.example.yxy.ceo.controller.fragment.RegisterSignFragment;
import com.example.yxy.ceo.controller.fragment.SignFragment;
import com.example.yxy.ceo.controller.fragment.SiteNavigationFragment;
import com.example.yxy.ceo.controller.fragment.SitingGuideFragment;
import com.example.yxy.ceo.controller.fragment.WeatherForecastFragment;
import com.example.yxy.ceo.model.Model;
import com.example.yxy.ceo.model.OnItemClickListener;
import com.example.yxy.ceo.model.util.ToastUtil;
import com.example.yxy.ceo.view.autolayout.AutoLayoutActivity;

public class MainActivity extends AutoLayoutActivity {

    private TextView tv_title_main_name;
    private ImageView iv_main_iamge;
    private RecyclerView rv_main_list;
    private MenuMainAdapter mMenuMainAdapter;
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersionStatus();
        setContentView(R.layout.activity_main);

        //添加activity管理器
        ActivityManager.getInstance().addActivity(this);

        intitView();

        initData();
        setListener();
    }

    private void setListener() {
        //item点击事件
       mMenuMainAdapter.setmOnItemClickListener(new OnItemClickListener() {
           @Override
           public void onItemClick(RecyclerView.ViewHolder parent, View view, int position) {
               startCommenActivity(switchFragment(position));
           }
       });
        //头部图片点击事件
        iv_main_iamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private Class switchFragment(int position) {
        switch (position) {
            case 0:
                if(Model.getInstance(this).isLogin()){
                    //个人资料
                    return RegisterSignFragment.class;
                }
                //邀请码
                return SignFragment.class;
            case 1:
                //会议议程
                return AgenderMeetingFragment.class;
            case 2:
                //会场导航
                return SiteNavigationFragment.class;
            case 3:
                //座次引导
                return SitingGuideFragment.class;
            case 4:
                //会务服务
                return BusinessServiceFragment.class;
            case 5:
                //机场风采
                return AirportStyleFragment.class;
            case 6:
                //资料下载
                return DataDownloadFragment.class;
            case 7:
                //天气预报
                return WeatherForecastFragment.class;
            case 8:
                //FAQ
                return FaqFragment.class;
        }
        return null;
    }

    private void startCommenActivity(Class clz) {
        startCommenActivity(clz, null);
    }

    private void startCommenActivity(Class clz, Bundle bundle) {
        Intent intent = new Intent(MainActivity.this, CommenActivity.class);
        if (bundle == null) bundle = new Bundle();
        bundle.putString(CommenActivity.FRAGMENT_NAME, clz.getName());
//        bundle.putSerializable("", clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initData() {
        iv_main_iamge.setBackgroundResource(R.drawable.head_main_image);
        tv_title_main_name.setText(R.string.title_text_main);

        rv_main_list.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mMenuMainAdapter = new MenuMainAdapter(this);
        rv_main_list.setAdapter(mMenuMainAdapter);

    }

    private void intitView() {
        tv_title_main_name = (TextView) findViewById(R.id.tv_title_main_name);
        iv_main_iamge = (ImageView) findViewById(R.id.iv_main_iamge);
        rv_main_list = (RecyclerView) findViewById(R.id.rv_main_list);
    }

    /**
     * 设置主题样式
     */
    private void setImmersionStatus() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//如果是MainActivity，则提示再次点击退出
            long secondTime = System.currentTimeMillis();

            if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
                firstTime = secondTime;
                ToastUtil.show(this, "再按一次退出CEO论坛", 1500);
                return true;
            } else {
                if (secondTime - firstTime > 800) {
                    firstTime = secondTime;
                    return super.onKeyDown(keyCode, event);
                } else {
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        //所有activity出栈
        ActivityManager.getInstance().finishAllActivity();
        super.onDestroy();
    }
}
