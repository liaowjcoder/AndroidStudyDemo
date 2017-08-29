package com.zeal.animation_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

/*
View动画：
    XML形式
        res/anim/xxx.xml
    代码形式
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.button);

        //xml方式去编写动画
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
//
//        button.startAnimation(animation);

        //代码的方式编写动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f,1.5f);
        alphaAnimation.setDuration(3000);
        button.startAnimation(alphaAnimation);
    }
}
