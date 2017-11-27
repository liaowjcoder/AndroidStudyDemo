package org.stagex.danmaku.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;

import org.fungo.fungolive.R;

import java.io.File;

/**
 * @author ZhuangQinDa
 *   图片加载库：封装glide库
 */
public class ImageLoader {

    private static volatile ImageLoader instance;
    private static Context mContext;

    private static final String DRAWABL_PREFIX = "drawable://";
    public static final String STATIC_FRAME = DRAWABL_PREFIX + R.drawable.live_default_block_frame;

    public static void init(Context context) {
        if(instance == null) {
            synchronized(ImageLoader.class) {
                if(instance == null) {
                    instance = new ImageLoader();
                    mContext = context;
                }
            }
        }
    }

    public static ImageLoader getInstance() {
        if(instance == null) {
            synchronized(ImageLoader.class) {
                if(instance == null) {
                    instance = new ImageLoader();
                }
            }
        }

        return instance;
    }

    protected ImageLoader() {
    }

    /**
     * 默认方式加载图片
     */
    public void displayImage(String url, ImageView imageView) {
        displayImage(url, imageView, null);
    }

    public void loadImage(String url, ImageLoadCallback callback) {
        loadImage(url, null, callback);
    }

    /**
     * 加载图片
     */
    public void displayImage(Context context, String url, ImageView imageView, DisplayImageOptions options) {
        DrawableTypeRequest builder = Glide.with(context).load(url);
        if(options != null) {
            if(TextUtils.isEmpty(url) && options.shouldShowImageForEmptyUri() ) {
                imageView.setImageDrawable(options.getImageForEmptyUri(context.getResources()));
                return;
            }
            builder.skipMemoryCache(options.isCacheInMemory());
            builder.diskCacheStrategy(options.isCacheOnDisk() ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE);
            if (options.shouldShowImageOnLoading()) {
                builder.placeholder(options.getImageOnLoading(context.getResources()));
            }
            if (options.shouldShowImageOnFail()) {
                builder.error(options.getImageOnFail(context.getResources()));
            }
        }
        builder.into(imageView);
    }

    /**
     * 加载图片
     */
    public void displayImage(String url, ImageView imageView, DisplayImageOptions options) {
        DrawableTypeRequest builder;
        if (url != null && url.startsWith(DRAWABL_PREFIX)) {
            Integer resId = StringUtils.parseInt(url.substring(DRAWABL_PREFIX.length()));
            Logger.e("url: "+url+", resId: " + resId);
            builder = Glide.with(mContext).load(resId);
        }
        else {
            builder = Glide.with(mContext).load(url);
        }

        if(options != null) {
            if(TextUtils.isEmpty(url) && options.shouldShowImageForEmptyUri() ) {
                imageView.setImageDrawable(options.getImageForEmptyUri(mContext.getResources()));
                return;
            }
            builder.skipMemoryCache(options.isCacheInMemory());
            builder.diskCacheStrategy(options.isCacheOnDisk() ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE);
            if (options.shouldShowImageOnLoading()) {
                builder.placeholder(options.getImageOnLoading(mContext.getResources()));
            }
            if (options.shouldShowImageOnFail()) {
                builder.error(options.getImageOnFail(mContext.getResources()));
            }
        }
        builder.into(imageView);
    }

    public void displayImageForRoundCorner(int resId, ImageView imageView, float radius) {
        Glide.with(mContext).load(resId).asBitmap().into(getRoundCornerTarget(imageView, radius));
    }

    private static BitmapImageViewTarget getRoundCornerTarget(final ImageView imageView, final float radius) {
        BitmapImageViewTarget bitmapImageViewTarget = new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCornerRadius(radius);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        };
        return bitmapImageViewTarget;
    }

    /**
     * 加载图片
     * @param callback 获得图片后回调
     */
    public void loadImage(String url,DisplayImageOptions options, final ImageLoadCallback callback) {

        DrawableTypeRequest builder = Glide.with(mContext).load(url);
        if(options != null) {
            builder.skipMemoryCache(options.isCacheInMemory());
            builder.diskCacheStrategy(options.isCacheOnDisk() ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE);
            if (options.shouldShowImageForEmptyUri()) {
                builder.placeholder(options.getImageForEmptyUri(mContext.getResources()));
            }
            if(options.shouldShowImageOnLoading()) {
                builder.placeholder(options.getImageOnLoading(mContext.getResources()));
            }
            if (options.shouldShowImageOnFail()) {
                builder.error(options.getImageOnFail(mContext.getResources()));
            }
        }
        builder.into(new SimpleTarget<GlideBitmapDrawable>(SimpleTarget.SIZE_ORIGINAL, SimpleTarget.SIZE_ORIGINAL) {
            @Override
            public void onResourceReady(GlideBitmapDrawable resource, GlideAnimation<? super GlideBitmapDrawable> glideAnimation) {
                if (callback != null) {
                    if (resource != null) {
                        callback.onSuccess(resource.getBitmap());
                    } else {
                        callback.onFailure();
                    }
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if (callback != null) {
                    callback.onFailure();
                }
            }
        });
    }

    /**
     * 清除内存中的缓存 必须在UI线程中调用
     */
    public void clearMemory() {
        Glide.get(mContext).clearMemory();
    }

    /**
     * 清除磁盘中的缓存 必须在后台线程中调用，建议同时clearMemory()
     */
    public void clearDiskCache() {
        Glide.get(mContext).clearDiskCache();
    }

    /**
     * 优先级加载图片
     * @param priority  优先级  Priority.LOW/Priority.HIGH
     */
    public void displayImageWithPriority(String url,ImageView imageView,Priority priority) {
        Glide.with(mContext).load(url).priority(priority).into(imageView);
    }


    /**
     * 本地文件加载
     */
    public void loadFile(String path, ImageView imageView) {
        File file = new File(path);
        Glide.with(mContext).load(file).into(imageView);
    }

    /**
     * 资源文件加载
     */
    public void loadResource(int resourceId, ImageView imageView) {
        Glide.with(mContext).load(resourceId).into(imageView);
    }

    public interface ImageLoadCallback {
        void onSuccess(Bitmap bitmap);

        void onFailure();
    }

}
