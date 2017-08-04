package com.zeal.routerdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //跳转到第SecondActivity
        findViewById(R.id.click2SecondAct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转操作-不带参数的
                //startActivity(new Intent(MainActivity.this,SecondActivity.class));
                //ARouter.getInstance().build("/test/activity").navigation();

                //跳转操作-携带参数的
                //with携带参数类型：跟startActivity可以携带的参数类型差不多
                Bundle bundle = new Bundle();
                bundle.putString("bundleStr","i am bundle data");
                ARouter.getInstance().build("/test/activity")
                        .withString("name","zeal")
                        .withInt("age",25)
                        .withBundle("bundle",bundle)
                        .navigation();

            }
        });

        //关闭ARouter
        findViewById(R.id.destroy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().destroy();
            }
        });

        //测试startActivityForResult
        findViewById(R.id.forresult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/test/activity01")
                        .withString("name","zeal")
                        .navigation(MainActivity.this,66);
            }
        });
        //测试查找fragment
        findViewById(R.id.getFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = (Fragment) ARouter.getInstance().build("/test/fragment")
                        .navigation();

                if(fragment!=null) {
                    Toast.makeText(MainActivity.this, "找到fragment："+fragment.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        //测试应用内携带参数跳转 它会查找 /test/activity02 对应的activity
        findViewById(R.id.normalNavigationWithParams).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri testUriMix = Uri.parse("http://abc/test/activity02");
                ARouter.getInstance().build(testUriMix)
                        .withString("key","values")
                        .navigation();
            }
        });
        //测试通过url跳转
        findViewById(R.id.navByurl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ARouter.getInstance().build("/test/webactivity")
                        .withString("url","http://www.baidu.com")
                        .navigation();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==66) {
            Log.e("zeal","在 onActivityResult 拿到resultCode="+resultCode);
        }
    }
}
