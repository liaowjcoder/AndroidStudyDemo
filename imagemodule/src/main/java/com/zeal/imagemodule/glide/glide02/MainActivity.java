package com.zeal.imagemodule.glide.glide02;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.NoTransition;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.zeal.imagemodule.R;

import java.io.File;

/**
 * Created by liaowj on 2017/7/18.
 * placeholder
 * placeholder 加载过程显示的图片
 * error 加载出错的情况
 * fallback load 参数model为空的情况，如果为空但是没有设置fallback那么后台就会报错log：Received null model
 * <p>
 * 注意：placeholder 不是异步的，他是在主线程中从本地中加载出来的，因此 glide 建议尽量使用小一点方便缓存。
 * <p>
 * <p>
 * Transformations:转换器，例如显示转化为原型图片等操作就使用这个接口实现的
 * apply(..)
 * <p>
 * centerCrop
 * circleCropTransform()...
 */

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageview);
        imageView2 = (ImageView) findViewById(R.id.imageview2);
        testApplyingTransformations();

    }

    public void testPlaceHolders() {

        RequestOptions requestOptions = new RequestOptions();
        //PlaceHolders的设置
        //加载过程
        requestOptions.placeholder(R.mipmap.ic_launcher);
        //加载出错
        requestOptions.error(R.mipmap.g2);
        //load方法参数为空的情况
        requestOptions.fallback(R.mipmap.g1);

        Glide.with(this).setDefaultRequestOptions(requestOptions)
                .load("http://img1.imgtn.bdimg.com/it/u=543045184,2055997095&fm=26&gp=0.jpg")
                .into(imageView);
    }

    public void testTransformations() {
        Glide.with(this)
                .load("http://img4.imgtn.bdimg.com/it/u=13352235,3390639229&fm=26&gp=0.jpg")
                //.apply(RequestOptions.fitCenterTransform())//centerCrop
                .apply(RequestOptions.circleCropTransform())//circleCrop
                .into(imageView);
    }

    public void testTransitionOptions() {
        Glide.with(this)
                .load("http://img4.imgtn.bdimg.com/it/u=275932335,3390313147&fm=26&gp=0.jpg")
                .apply(RequestOptions.placeholderOf(R.mipmap.g1))
                //.transition(DrawableTransitionOptions.withCrossFade(5000))//渐变效果
                .transition(new DrawableTransitionOptions().crossFade(5000))
                .into(imageView);
    }


    //获取一个 RequestBuilder
    public void testRequestBuilder() {

        //Drable
        RequestBuilder<Drawable> manager = Glide.with(this).asDrawable();

        //Bitmap
        RequestBuilder<Bitmap> bitmapRequestBuilder = Glide.with(this).asBitmap();

        RequestBuilder<File> fileRequestBuilder = Glide.with(this).asFile();

        RequestBuilder<GifDrawable> gifDrawableRequestBuilder = Glide.with(this).asGif();
    }

    public void testApplyingRequestOptions() {

        RequestBuilder<Drawable> requestBuilder = Glide.with(this).asDrawable().apply(RequestOptions.circleCropTransform());

        //RequestBuilder是可以多次被使用的
        requestBuilder.load("1").into(imageView);
        requestBuilder.load("2").into(imageView);
        requestBuilder.load("3").into(imageView);
        requestBuilder.load("4").into(imageView);

    }

    public void testApplyingTransformations() {

        //ReqeustOption内置了几个 Transformations
        //centerCropTransform()
        //circleCropTransform()
        //noTransform()
        //fitCenterTransform()
        //centerInsideTransform()
        //

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA);
        //requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(this).asDrawable()
                .load("http://img5.imgtn.bdimg.com/it/u=2640570473,212514906&fm=26&gp=0.jpg")
                .apply(requestOptions)
                .into(imageView);
//        Glide.with(this).asDrawable()
//                .load("http://img0.imgtn.bdimg.com/it/u=1777277829,1559419401&fm=26&gp=0.jpg")
//                .apply(RequestOptions.fitCenterTransform())
//                .into(imageView2);

        //Class<Drawable> drawableClass = Drawable.class;

    }

    public void testTargets() {
        //注意 into 方法不仅仅是用于开始加载一个资源到imageview上，它也可以指定一个target对象。
        //target 可以接受请求的结果，into(imageView)内部会将其该imageview进行转化为指定的target对象。
        //Glide.with(this).load("").into(target);
        //Glide.with(this).load("").into(imageView);

        //移除一个target
        // Glide.with(this).clear(target);

    }

    public void testCacheStrategies() {
        RequestOptions op = new RequestOptions();
        op.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).asDrawable().apply(op).into(imageView);
    }
}
