package example2;

/**
 * Created by zeal on 2017/7/3.
 */

public class ThreadDemo {

    /*
    多个线程操作同一个对象的同一个方法，因为是多线程并发访问的，因此具有先后顺序。
    那么多个线程操作 myObject.method() 方法所使用的锁是当前的 myObject 对象吗？
    bengin method threadName = Thread-0
    bengin method threadName = Thread-1
    end
    end

    给 method 方法加入 synchronized 修饰
    bengin method threadName = Thread-0
    end
    bengin method threadName = Thread-1
    end
    可以看到上面的输出结果是同步执行的，因此可以认为他们是共享一把锁的。
    调用了 synchronized 关键字声明的方法一定是排队执行的，另外记住"共享"这两个字
    只要在共享字眼的读写访才需要同步化，如果不是共享资源，那根本就没有同步的必要。
     */
    public static void main(String[] args) {
        MyObject myObject = new MyObject();

        ThreadA threadA = new ThreadA(myObject);
        ThreadB threadB = new ThreadB(myObject);


        threadA.start();
        threadB.start();
    }
}
