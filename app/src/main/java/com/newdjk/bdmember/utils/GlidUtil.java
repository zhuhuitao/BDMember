package com.newdjk.bdmember.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.newdjk.bdmember.R;

public class GlidUtil {
    public static void loadImage(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                .error(R.mipmap.ic_group2)
                .placeholder(R.mipmap.ic_group2)
                .into(iv);
    }
}
