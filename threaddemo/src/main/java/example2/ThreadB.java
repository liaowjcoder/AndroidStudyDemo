package example2;

/**
 * Created by zeal on 2017/7/3.
 */

public class ThreadB extends Thread {

    private MyObject myObject;

    public ThreadB(MyObject myObject) {
        super();
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        myObject.method();
    }
}
