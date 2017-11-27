package com.zeal.viewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by zeal on 2017/11/23.
 */

public class ViewGroupDemo extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Random mRandom;

    public ViewGroupDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.RED);
        mRandom = new Random();

    }


    private boolean isDraw;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mPaint.setColor(Color.YELLOW);
        int left1 = 0;
        int right1 = 0;
        int top1 = 0;
        int bottom1 = 0;
        //第一个
        left1 = 0;
        right1 = (int) (getWidth() * 0.22f);
        top1 = 0;
        bottom1 = (int) (getHeight() * 0.39);
        canvas.drawRect(left1, top1, right1, bottom1, mPaint);


        drawCircle(canvas, left1, right1, top1, bottom1, 0.41f);


        //第二个
        mPaint.setColor(Color.WHITE);
        int left2 = (int) (getWidth() * 0.22f);
        int right2 = right1 + (int) (getWidth() * 0.47f);
        int top2 = 0;
        int bottom2 = top2 + (int) (getHeight() * 0.39);
        canvas.drawRect(left2, top2, right2, bottom2, mPaint);

        drawCircle(canvas, left2, right2, top2, bottom2, 0.28f);

        //第三个
        int left3 = right2;
        int right3 = getWidth();
        int top3 = 0;
        int bottom3 = (int) (getHeight() * 0.24);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(left3, top3, right3, bottom3, mPaint);

        drawCircle(canvas, left3, right3, top3, bottom3, 0.28f);

        //第4个
        int left4 = 0;
        int right4 = (int) (getWidth() * 0.28f);
        int top4 = bottom1;
        int bottom4 = bottom1 + (int) (getHeight() * 0.35f);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(left4, top4, right4, bottom4, mPaint);

        drawCircle(canvas, left4, right4, top4, bottom4, 0.47f);
        //第5个
        int left5 = right4;
        int right5 = (int) (getWidth() * 0.41f) + left5;
        int top5 = bottom2;
        int bottom5 = bottom2 + (int) (getHeight() * 0.30f);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(left5, top5, right5, bottom5, mPaint);
        drawCircle(canvas, left5, right5, top5, bottom5, 0.46f);
        //第6个
        int left6 = right5;
        int right6 = getWidth();
        int top6 = bottom3;
        int bottom6 = bottom3 + (int) (getHeight() * 0.36f);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(left6, top6, right6, bottom6, mPaint);

        drawCircle(canvas, left6, right6, top6, bottom6, 0.41f);
        //第7个
        int left7 = 0;
        int right7 = (int) (getWidth() * 0.28f);
        int top7 = bottom4;
        int bottom7 = getHeight();
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(left7, top7, right7, bottom7, mPaint);
        drawCircle(canvas, left7, right7, top7, bottom7, 0.32f);
        //第8个
        int left8 = right7;
        int right8 = (int) (getWidth() * 0.47f) + left8;
        int top8 = bottom5;
        int bottom8 = getHeight();
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(left8, top8, right8, bottom8, mPaint);
        drawCircle(canvas, left8, right8, top8, bottom8, 0.28f);
        //第9个
        int left9 = right8;
        int right9 = getWidth();
        int top9 = bottom6;
        int bottom9 = getHeight();
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(left9, top9, right9, bottom9, mPaint);
        drawCircle(canvas, left9, right9, top9, bottom9, 0.28f);
        isDraw = true;
    }

    private void drawCircle(Canvas canvas, int left1, int right1, int top1, int bottom1, float per) {
        boolean isSelectWidth = (right1 - left1) < (bottom1 - top1);

        int width1 = right1 - left1;
        int height1 = bottom1 - top1;

        int r = 0;
        if (isSelectWidth) {
            r = (int) (width1 * per);
        } else {
            r = (int) (height1 * per);
        }

        int offset = width1 - 2 * r;
        float percent = 0;
        while (percent == 0) {
            percent = mRandom.nextInt(100) * 1.0f / 100;
            System.out.println(percent);
        }
        int lOffset = (int) (percent * offset);
        System.out.println("lOffset:" + lOffset);
        int rOffset = (int) ((1 - percent) * offset);
        System.out.println("rOffset:" + rOffset);

        int count = 0;
        while (true) {
            if (count < (height1 - offset - 2 * r)) {
                count += r;
                continue;
            }
            break;
        }


        int minY = lOffset + r;
        int maxY = height1 - rOffset - r;
        int range = maxY - minY - 1;
        int randVal = mRandom.nextInt(range);
        int y = randVal + 1 + minY;

        mPaint.setColor(Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(left1 + lOffset + r, top1 + y, r, mPaint);
    }
}
