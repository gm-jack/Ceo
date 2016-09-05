package com.example.yxy.ceo.model.net;

import android.content.Context;

import com.example.yxy.ceo.model.Model;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;

/**
 * Created by yxy
 * on 2016/9/2.
 */

public class HttpUtils {
    private Context mContext;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient mClient;

    public HttpUtils(Context context) {
        mContext = context;
        mClient = new OkHttpClient();
    }

    public void post(final String url, final String json, final Callback callback) throws IOException {
        Model.getInstance(mContext).getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                RequestBody body = RequestBody.create(JSON, json);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                mClient.newCall(request).enqueue(callback);
            }
        });
    }
}
