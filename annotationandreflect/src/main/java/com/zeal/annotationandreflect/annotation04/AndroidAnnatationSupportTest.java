package com.zeal.annotationandreflect.annotation04;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zeal on 2017/9/3.
 * <p>
 * 1.@Nullable:
 * 标识：局部变量，实例变量，方法返回值都可以为null
 * 它没有任何属性
 * 生命周期：
 * <p>
 * 2.资源标识符 @LayoutRes @StringRes @StyleRes  @AnimRes @RawRes  @AttrRes @DrawableRes
 * @Menu  @IdRes
 * <p>
 * 3.线程相关 @WorkThread @AnyThread @MainThread
 *
 * 4.大小限制
 *  @Size  方法返回值，形参，实例变量等的大小限制，也可以指定最大和最小的size
 *  @IntRange
 *  @FloatRange
 *
 *  5.调用相关
 *  @CallSuper 该方法调用时必须先调用父类的该方法先。
 *
 *  6.数据相关
 *  @StringDef @IntDef
 *
 *
 */

public class AndroidAnnatationSupportTest extends AppCompatActivity {


    @Size(2)
    private int[] arr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(1);

        arr = new int[]{1,3};



        new Thread() {
            @Override
            public void run() {
                super.run();
                test();
            }
        };

        //test();


        //add
        add(new int[]{1, 2});
        //add(new int[]{1,2,3});


        //test RequireApi
        //test2();


        //test FloatRange
        setAlpha(0.75f);

        //test DrawableRes
        setDrawable(android.R.mipmap.sym_def_app_icon);


        //test IdRes
        //setId(R.id.linearlayout);

    }


    //@AnyThread
    @WorkerThread
    private void test() {

    }

    private void add(@Size(value = 2/*, min = 0, max = 3*/) int[] arr) {

    }

    private @Size(3) int[] getDatas() {
        return new int[]{1,2,3};
    }


    //指定当前该方法最低的调用版本需要是23，也就是minSdk是23至少
    @RequiresApi(23)
    private void test2() {

    }

    //取值约束
    public @IntRange(from =1,to = 10) int getCurrentData() {
        return 10;
    }


    private void setAlpha(@FloatRange(from = 0,to = 1) float alpha) {

    }

    private void setDrawable(@DrawableRes int drawable){

    }


    private void setId(@IdRes int id) {

    }
}
