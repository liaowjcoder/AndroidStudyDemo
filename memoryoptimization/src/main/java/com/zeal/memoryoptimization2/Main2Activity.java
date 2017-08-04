package com.zeal.memoryoptimization2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zeal.memoryoptimization.R;

import java.util.Random;
/*
使用String进行拼接的结果：
08-03 09:49:21.349 26830-26830/com.zeal.memoryoptimization I/zeal: String start...
08-03 09:49:21.349 26830-26830/com.zeal.memoryoptimization I/zeal: String 0
08-03 09:49:23.031 26830-26830/com.zeal.memoryoptimization I/zeal: String 1
08-03 09:49:25.958 26830-26830/com.zeal.memoryoptimization I/zeal: String 2
08-03 09:49:30.229 26830-26830/com.zeal.memoryoptimization I/zeal: String 3
08-03 09:49:35.774 26830-26830/com.zeal.memoryoptimization I/zeal: String 4
08-03 09:49:42.877 26830-26830/com.zeal.memoryoptimization I/zeal: String 5
08-03 09:49:50.577 26830-26830/com.zeal.memoryoptimization I/zeal: String 6
08-03 09:50:01.068 26830-26830/com.zeal.memoryoptimization I/zeal: String 7
08-03 09:50:08.696 26830-26830/com.zeal.memoryoptimization I/zeal: String 8
08-03 09:50:16.828 26830-26830/com.zeal.memoryoptimization I/zeal: String 9
08-03 09:50:28.077 26830-26830/com.zeal.memoryoptimization I/zeal: String end...
时间：
    49：21.349~50：28.077 一分多钟
    这里会出现内存抖动现象严重

08-03 09:52:58.401 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder start...
08-03 09:52:58.422 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 0
08-03 09:52:58.424 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 1
08-03 09:52:58.432 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 2
08-03 09:52:58.457 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 3
08-03 09:52:58.462 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 4
08-03 09:52:58.466 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 5
08-03 09:52:58.469 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 6
08-03 09:52:58.472 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 7
08-03 09:52:58.511 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 8
08-03 09:52:58.514 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder 9
08-03 09:52:58.514 26830-26830/com.zeal.memoryoptimization I/zeal: StringBuilder end...
时间：
    52：58.401~52：58.514 113ms
    效率非常高，没有内存抖动现象


 */
public class Main2Activity extends AppCompatActivity {

    private static int ROW = 10;
    private static int LENGTH = 4000;
    private static int[][] arr = new int[ROW][LENGTH];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Random random = new Random();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < LENGTH; j++) {
                arr[i][j] = random.nextInt();
            }
        }

        //使用Strin拼接字符串测试拼接时间
        findViewById(R.id.string).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "";
                Log.i("zeal","String start...");
                for (int i = 0; i < ROW; i++) {
                    Log.i("zeal", "String " + i);
                    for (int j = 0; j < LENGTH; j++) {
                        str += arr[i][j];
                    }
                }
                Log.i("zeal","String end...");
            }

        });
        //使用StringBuilder拼接字符串测试拼接时间
        findViewById(R.id.stringbuilder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zeal","StringBuilder start...");
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < ROW; i++) {
                    for (int j = 0; j < LENGTH; j++) {
                        str.append(arr[i][j]);
                    }
                    Log.i("zeal", "StringBuilder " + i);
                }
                Log.i("zeal","StringBuilder end...");
            }
        });


    }
}
