package com.zeal.imagemodule.glide.glide01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zeal.imagemodule.R;

/**
 * Created by liaowj on 2017/7/18.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageview);

        Glide.with(this).load("data:image/jpeg;base64,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").into(imageView);

        //回收资源
        //事实上glide自己会根据绑定的activity/fragment生命周期去回收这些资源。
        //内部就是将 imgageview setBackgroundResource(null)
        Glide.with(this).clear(imageView);
    }
}
