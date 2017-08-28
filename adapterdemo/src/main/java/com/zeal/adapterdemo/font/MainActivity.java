package com.zeal.adapterdemo.font;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.TypedValue;
import android.widget.TextView;

import com.zeal.adapterdemo.R;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        //保证系统文字大小发生改变不影响app文字大小的改变
        if(newConfig.fontScale!=-1) {
            //非默认值
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {


        Resources res= super.getResources();
        if(res.getConfiguration().fontScale!=-1) {
            Configuration newConf = new Configuration();

            newConf.setToDefaults();

            res.updateConfiguration(newConf,res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text);

        //自适应缩放字体大小，这里需要指定最大显示
        adjustTextSize(textView,dip2px(this,200),textView.getText().toString());


    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void adjustTextSize(TextView tv, int maxWidth, String text){


        int avaiWidth = maxWidth-tv.getPaddingLeft()-tv.getPaddingRight()-10;

        if(avaiWidth<=0) {
            return;
        }


        TextPaint textPaint = new TextPaint(tv.getPaint());

        float textSize = textPaint.getTextSize();

        while(textPaint.measureText(text)>avaiWidth) {
            textSize--;
            textPaint.setTextSize(textSize);
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);

    }
}
