package com.zeal.imagemodule.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zeal.imagemodule.R;

public class TestActivity extends AppCompatActivity {
    ImageView backImage;
    ImageView srcImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        backImage = (ImageView) findViewById(R.id.iv01);
        srcImage = (ImageView) findViewById(R.id.iv02);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bgDrawable = (BitmapDrawable) backImage.getBackground();
                if (bgDrawable != null) {
                    Bitmap bitmap = bgDrawable.getBitmap();
                    int allocationByteCount = bitmap.getAllocationByteCount();
                    int byteCount = bitmap.getByteCount();

                    Log.e("zeal", "设置了Background的bitmap大小：allocationByteCount：" + allocationByteCount +
                            "byteCount:" + byteCount
                    );
                }
                Log.e("zeal", "----------");
                BitmapDrawable srcDrawable = (BitmapDrawable) srcImage.getDrawable();
                if (srcDrawable != null) {
                    Bitmap bitmap = srcDrawable.getBitmap();
                    int allocationByteCount = bitmap.getAllocationByteCount();
                    int byteCount = bitmap.getByteCount();
                    Log.e("zeal", "设置了src的bitmap大小：allocationByteCount：" + allocationByteCount +
                            "byteCount:" + byteCount
                    );
                    Log.e("zeal","bm width: "+bitmap.getWidth() );
                    Log.e("zeal","bm height: "+bitmap.getHeight() );
                }
                printDisply();
            }
        });
    }

    private void printDisply() {
        WindowManager manager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int screenHeight = outMetrics.heightPixels;
        int screenWidth = outMetrics.widthPixels;

        Log.e("zeal", "width = " + screenWidth);
        Log.e("zeal", "heoght = " + screenHeight);
    }
}
