package com.example.yxy.ceo.controller.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yxy.ceo.R;
import com.example.yxy.ceo.base.BaseFragment;
import com.example.yxy.ceo.model.Info.UserEntity;
import com.example.yxy.ceo.model.Model;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * 签到界面
 */
public class SignFragment extends BaseFragment implements View.OnClickListener {

    private ImageView iv_title_main_back;
    private TextView tv_title_main_name, tv_sign_text;
    private EditText et_sign_text;
    private Button btn_sign_text;
    private Callback responseCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            Log.e("TAG", "onFailure()");
        }

        @Override
        public void onResponse(Response response) throws IOException {
            Log.e("TAG", "onResponse()   " + response.body().string());
        }
    };

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        iv_title_main_back = (ImageView) view.findViewById(R.id.iv_title_main_back);
        tv_title_main_name = (TextView) view.findViewById(R.id.tv_title_main_name);
        et_sign_text = (EditText) view.findViewById(R.id.et_sign_text);
        btn_sign_text = (Button) view.findViewById(R.id.btn_sign_text);
        tv_sign_text = (TextView) view.findViewById(R.id.tv_sign_text);

        iv_title_main_back.setVisibility(View.VISIBLE);

        Log.e("TAG", mContext.getResources().getString(R.string.sign_tv_text));
        String signText = mContext.getResources().getString(R.string.sign_tv_text);
        SpannableString mSpannableString = new SpannableString(signText);
        mSpannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //个人资料
                getHoldingActivity().addFragment(new RegisterSignFragment());
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#6FC7EF"));
                ds.setUnderlineText(true);
            }
        }, 0, signText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_sign_text.setMovementMethod(LinkMovementMethod.getInstance());
        tv_sign_text.setText(mSpannableString);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign;
    }

    @Override
    protected void initData() {
        tv_title_main_name.setText(R.string.title_text_sign);

        initListener();
    }

    private void initListener() {
        iv_title_main_back.setOnClickListener(this);
        btn_sign_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_main_back:
                //返回
                getHoldingActivity().finish();
                break;
            case R.id.btn_sign_text:
                //提交
                String invatation = et_sign_text.getText().toString();

                try {
                    Model.getInstance(mContext).getHttp().post("http://airtest.rtmap.com/ceoforum/login.do", new Gson().toJson(new UserEntity("徐海龙", "job2", "test", "123456789", "xuhai@123.com", "11111", "I")),responseCallback);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
