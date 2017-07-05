package com.zeal.ipc6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zeal.ipc.R;
import com.zeal.ipc4.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/*
当开启多个进程时，那么 MyApplication 会创建多次，通过在 MyApplication
 oncreate 中打印 log 可以知道印证这个结论。


 */
public class MainActivity extends AppCompatActivity {
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);

        textview.setText("MainActivity");

    }

    public void onclick(View view) {

        startActivity(new Intent(this, Main2Activity.class));

    }

    @Override
    protected void onResume() {
        super.onResume();


        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    User user = new User("zeal", 25, true);
                    File file = new File(getExternalCacheDir(), "user.txt");
                    Log.e("zeal", "mainactivity:" + user.name + "--" + user.age + "--" + user.isMale);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

                    oos.writeObject(user);

                    oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
