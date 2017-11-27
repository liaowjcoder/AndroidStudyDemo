package com.zeal.memoryoptimization5_bitmap_recycle;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.zeal.memoryoptimization.R;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        final ImageView imageView = (ImageView) findViewById(R.id.imageview);

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);

                    BitmapDrawable bd  = (BitmapDrawable) imageView.getDrawable();

                    Bitmap bitmap = bd.getBitmap();

                    int byteCount = bitmap.getByteCount();

                    Log.e("zeal","byteCount:"+byteCount);


                } catch (InterruptedException e) {
                    e.printStackTrace();


                }
            }
        }.start();

    }
}
