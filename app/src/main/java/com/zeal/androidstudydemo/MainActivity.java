package com.zeal.androidstudydemo;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends AppCompatActivity {

    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        //System.out.println("CPU_COUNT:" + CPU_COUNT);//4

        //final Toast toast = Toast.makeText(this, "线程A", Toast.LENGTH_SHORT);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                for (int i = 0; i < 500; i++) {
//                    //toast.setText("线程A：--" + i);toast.show();
//                    //Log.e("zeal",i+"current timeA:"+ System.currentTimeMillis());
//                    Toast.makeText(MainActivity.this, "线程A： " + i, Toast.LENGTH_SHORT).show();
//                    //Log.e("zeal",i+"current timeB:"+ System.currentTimeMillis());
////                    toast.show();
//                    //这段代码的执行是异步的。
//                }
//                Looper.loop();
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                for (int i = 0; i < 500; i++) {
//                    Toast.makeText(MainActivity.this, "线程B：--" + i, Toast.LENGTH_SHORT).show();
//                }
//                Looper.loop();
//            }
//        }).start();
//        for (int i = 0; i < 500; i++) {
//            Toast.makeText(MainActivity.this, "Main线程： " + i, Toast.LENGTH_SHORT).show();
//        }
//        show(MainActivity.LENGTH_LONG);
//
//        imageview = (ImageView) findViewById(R.id.imageview);
//
//        Glide.with(this).load(R.mipmap.loading)
//
//                .into(imageview);

        //Toast.makeText(this, "taost message", 0).show();
    }

    public static void show(@Durations int duration) {
        int d = duration;
    }


    @IntDef({LENGTH_LONG, LENGTH_SHORT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Durations {
    }

    public static final int LENGTH_SHORT = 1;
    public static final int LENGTH_LONG = 2;

    //表示返回值必须要是 Durations 中指定的 @IntDef 中限制的值
    @Durations
    public int getValue(int value) {

//        return 0;
        return LENGTH_SHORT;
    }


}

