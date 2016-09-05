package com.example.yxy.ceo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yxy.ceo.R;
import com.example.yxy.ceo.model.Model;
import com.example.yxy.ceo.model.OnItemClickListener;
import com.example.yxy.ceo.model.util.Contance;

/**
 * Created by yxy on 2016/8/31.
 */

public class MenuMainAdapter extends RecyclerView.Adapter<MenuMainAdapter.MenuMainViewHolder> {
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public MenuMainAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MenuMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuMainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_main, parent, false));
    }

    @Override
    public void onBindViewHolder(final MenuMainViewHolder holder, final int position) {
        if (Model.getInstance(mContext).isChinese()) {
            holder.setData(Contance.zh_pictures[position]);
        } else {
            holder.setData(Contance.en_pictures[position]);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder, holder.itemView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Contance.zh_pictures.length;
    }

    public static class MenuMainViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_menu_main;
//        private TextView tv_menu_main;

        public MenuMainViewHolder(View itemView) {
            super(itemView);
            iv_menu_main = (ImageView) itemView.findViewById(R.id.iv_menu_main);
//            tv_menu_main = (TextView) itemView.findViewById(R.id.tv_menu_main);

        }

        public void setData(int picture) {
            iv_menu_main.setImageResource(picture);
        }
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
