package com.zeal.memoryoptimization3_viewstub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeal.memoryoptimization.R;

import java.util.Random;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        if(new Random().nextInt()%2==0) {
            ViewStub textViewStub= (ViewStub) findViewById(R.id.viewstub_textview);
            textViewStub.inflate();


            TextView text = (TextView) findViewById(R.id.text);

            text.setText("i am text");
        }else {
            ViewStub textViewStub= (ViewStub) findViewById(R.id.viewstub_imageview);
            textViewStub.inflate();


            ImageView image = (ImageView) findViewById(R.id.image);
            image.setImageResource(R.mipmap.ic_launcher);

        }
    }
}
