package com.zeal.ipc6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zeal.ipc.R;
import com.zeal.ipc3.Main3Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main2Activity extends AppCompatActivity {
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);
        textview.setText("Main2Activity");
    }

    public void onclick(View view) {

        startActivity(new Intent(this, Main3Activity.class));

    }

    @Override
    protected void onResume() {
        super.onResume();

        //取出共享文件保存的内容

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    File file = new File(getExternalCacheDir(), "user.txt");
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                    User user = (User) ois.readObject();

                    Log.e("zeal", "main2activity:" + user.name + "--" + user.age + "--" + user.isMale);
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
