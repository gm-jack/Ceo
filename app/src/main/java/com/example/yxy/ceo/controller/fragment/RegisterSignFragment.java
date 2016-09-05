package com.example.yxy.ceo.controller.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yxy.ceo.R;
import com.example.yxy.ceo.base.BaseFragment;

/**
 * 注册界面
 */
public class RegisterSignFragment extends BaseFragment {

    private ImageView iv_title_main_back;
    private TextView tv_title_main_name,tv_register_sign_sure;
    private EditText et_register_name,et_register_corporation,et_register_job,et_register_email,et_register_phone;
    private Button btn_register_sign;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        iv_title_main_back = (ImageView) view.findViewById(R.id.iv_title_main_back);
        tv_title_main_name = (TextView) view.findViewById(R.id.tv_title_main_name);
        btn_register_sign = (Button) view.findViewById(R.id.btn_register_sign);
        et_register_name = (EditText) view.findViewById(R.id.et_register_name);
        et_register_corporation = (EditText) view.findViewById(R.id.et_register_corporation);
        et_register_job = (EditText) view.findViewById(R.id.et_register_job);
        et_register_email = (EditText) view.findViewById(R.id.et_register_email);
        et_register_phone = (EditText) view.findViewById(R.id.et_register_phone);
        tv_register_sign_sure = (TextView) view.findViewById(R.id.tv_register_sign_sure);

        iv_title_main_back.setVisibility(View.VISIBLE);
        tv_title_main_name.setText(R.string.title_text_register);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initData() {
        initListener();

    }

    private void initListener() {
        iv_title_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHoldingActivity().finish();
            }
        });
    }
}
