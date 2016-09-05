package com.example.yxy.ceo.controller.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yxy.ceo.R;
import com.example.yxy.ceo.model.permission.FcPermissionsB;
import com.example.yxy.ceo.model.permission.impl.OnPermissionsDeniedListener;
import com.example.yxy.ceo.model.util.BitmapUtils;
import com.example.yxy.ceo.model.util.SPUtil;
import com.example.yxy.ceo.model.util.ScreenUtil;

import java.util.List;

public class SpalashActivity extends AppCompatActivity {

    public static final int RC_PERMISSIONS = 2333;
    public static final String SPALASH_TAG = "spalashactivity";
    private ImageView iv_spalash_image;
    private BitmapUtils mBitmapUtils;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startUpActivityDelay();
        }
    };
    private FcPermissionsB mFcPermissionsB;

    /**
     * 启动保存sp数据
     */
    private void startUpActivityDelay() {
        Intent intent = new Intent(SpalashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        //保存sp数据
        SPUtil.put(SpalashActivity.this, SPALASH_TAG, false);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        requestCameraPermission();
        mBitmapUtils = new BitmapUtils(this);

        initView();

        initData();
    }

    private void initData() {

        iv_spalash_image.setImageBitmap(mBitmapUtils.decodeSampleBitmapFromResource(ScreenUtil.getScreenWidth(this), ScreenUtil.getScreenHeight(this), R.drawable.image_spalash));
        //延迟跳转
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    private void initView() {
        iv_spalash_image = (ImageView) findViewById(R.id.iv_spalash_image);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    private void requestCameraPermission() {
        mFcPermissionsB = new FcPermissionsB.Builder(this)
                .rationale4ReqPer(getString(R.string.prompt_request_camara))
                .onDeniedListener(new OnPermissionsDeniedListener() {
                    @Override
                    public void onPermissionsDenied(int requestCode, List<String> perms) {
                        Toast.makeText(SpalashActivity.this, getString(R.string.prompt_been_denied), Toast.LENGTH_SHORT).show();
                    }
                })
                .rationale4NeverAskAgain(getString(R.string.prompt_we_need_camera))
                .requestCode(RC_PERMISSIONS)
                .build();
        mFcPermissionsB.requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mFcPermissionsB.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
