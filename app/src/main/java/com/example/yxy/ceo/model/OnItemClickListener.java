package com.example.yxy.ceo.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yxy on 2016/9/1.
 */

public interface OnItemClickListener {
    void onItemClick(RecyclerView.ViewHolder parent, View view, int position);
}
