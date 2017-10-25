package com.zeal.strictmodedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/*
1.内存泄露检测 可以检测，需要多次进入退出测试
2.流没有close 可以检测
3.在主线程io 可以检测
4.广播没有注册可以检测
5.sp直接在主线程读写数据是不允许的

 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //将当前对象添加到一个static的集合中，模拟内存泄露
//        MyApp.sLeakyActivities.add(this);



        //注册广播，但是不在onDestroy中取消注册，测试检测结果
//        registerReceiver(new MyReceiver(),new IntentFilter("action"));


        //在ui线程往磁盘中写入数据，不允许
        //SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        //sp.edit().putString("key","value");
        //sp.edit().commit();
        //String key = sp.getString("key", "");


        TextView textview = (TextView) findViewById(R.id.textview);
        textview.setText("MainActivity");




        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //检测不出来
                    //Thread.sleep(2000);

//                File exterStorage = Environment.getExternalStorageDirectory();
//
//                File destFile = new File(exterStorage,"dest.txt");
//
//                try{
                    ////可以检测
//                    if(!destFile.exists()) {
//                        destFile.createNewFile();
//                    }
//
//                    OutputStream os = new FileOutputStream(destFile,true);
//
//                    os.write("hello StrictMode".getBytes());
//
//                    os.flush();
//
                    //可以检测
//                    os.close();


//                    net();

                    startActivity(new Intent(MainActivity.this,Main2Activity.class));
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
//
//                startActivity(new Intent(MainActivity.this,Main2Activity.class));
//                finish();


            }
        });

    }

    private void net() {

//        URL url = new
//
//        try {
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    private class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }



}
