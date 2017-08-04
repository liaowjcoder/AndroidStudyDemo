package com.zeal.asynctaskdemo.asynctask01;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zeal.asynctaskdemo.R;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test04();
    }

    /**
     * 测试 AsyncTask@execute 能否在子线程中执行
     * <p>
     * test result：
     * onPreExecute:Thread-214
     * doInBackground:AsyncTask #1
     * onPostExecute:main
     * <p>
     * 结论：onPreExecute 方法实际的调用线程是在调用 execute 所在的线程中去执行的，
     * 并不一定是在主线程中去执行，但是 onPostExecute 是一定在主线程中执行的。
     */
    private void test01() {


        final AsyncTask task = new AsyncTask() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("zeal", "onPreExecute:" + Thread.currentThread().getName());
            }

            @Override
            protected Object doInBackground(Object[] params) {
                Log.e("zeal", "doInBackground:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Log.e("zeal", "onPostExecute:" + Thread.currentThread().getName());
            }
        };


        //在子线程中执行 execute 方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        }).start();

    }

    /**
     * 在 asynctask 内部通过定义一个全局常量 THREAD_POOL_EXECUTOR ，
     * 这样所有的 asynctask 都可以共享这个线程池。
     */
    private void test02() {

        //在外部定义一个线程池，可以是静态的，这样就可以让所有的 AsyncTask 去共享一个线程池 executor，
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                return null;
            }
        }.executeOnExecutor(executorService);

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                return null;
            }
        }.executeOnExecutor(executorService);
    }

    /*
    测试单一任务的执行
    我们使用默认的线程池去开启127个asynctask去执行，从打印结果可看出，它们是按顺序执行每一个任务的。

    ArrayDeque 是内部的存储结果，它的特点是无限制容量，因此可以无限制去创建 asynctask 去执行（单任务顺序执行）
    task:0#onPostExecute
    task:1#onPostExecute
    task:2#onPostExecute
    task:3#onPostExecute
    task:4#onPostExecute
    ...
    task:126#onPostExecute


    测试多任务的执行
    我们使用了 AsyncTask 中定义的 THREAD_POOL_EXECUTOR 作为线程池，这样就可以实现而并发去执行任务。
    task:2#onPostExecute
    task:0#onPostExecute
    task:1#onPostExecute
    task:3#onPostExecute
    task:4#onPostExecute
    task:5#onPostExecute


    //注意这是在4.4之后的版本，而其他版本的参数有一些变化。
    当 CPU_COUNT  = 4 时，
        线程池最大线程数量为 4*2+1 = 9
        阻塞队列为 128

        因此当并发的任务超过 9+ 128 = 137 时就会出现 RejectedExecutionException 异常

        pool size = 9 表示当前正在运行的任务有9个
        active threads = 9 表示当前活跃的线程有9个
        queued tasks = 128 表示当前队列中有128个任务了(也是最大的容量了，超过这个容量就要抛一异常了)


        下面的异常可以是在 ThreadPoolExecutor@toString 打印的：
        Caused by: java.util.concurrent.RejectedExecutionException:
        Task android.os.AsyncTask$3@386ddf08 rejected from java.util.concurrent.ThreadPoolExecutor@8ec96a1
        [Running, pool size = 9, active threads = 9, queued tasks = 128, completed tasks = 0]
        at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2011)

     */
    private void test03() {

        int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        Log.e("zeal", "CPU_COUNT:" + CPU_COUNT);//4
        //测试单一任务的执行
//        for (int i = 0; i < 309; i++) {
//            new MyAsyncTask().execute("task:" + i);
//        }

        //当创建的 asynctask 超过137个就会出现 RejectedExecutionException 异常。
        for (int i = 0; i < 138; i++) {
            //注意：只有在3.0后的版本才能使用
            new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "task:" + i);
        }
    }


    private void test04() {
        int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        Log.e("zeal", "CPU_COUNT:" + CPU_COUNT);
        int CORE_POOL_SIZE = 1;
        int MAXIMUM_POOL_SIZE = 6;
        int KEEP_ALIVE_SECONDS = 0;
        ThreadFactory sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
            }
        };
        BlockingQueue<Runnable> sPoolWorkQueue =
                new LinkedBlockingQueue<Runnable>(128);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                sPoolWorkQueue, sThreadFactory);
        //128+9=137
        for (int i = 0; i < 134; i++) {//138个 会挂掉
            //AsyncTask.THREAD_POOL_EXECUTOR
            threadPoolExecutor.execute(new Task("task#" + i, threadPoolExecutor));
        }


    }
}
