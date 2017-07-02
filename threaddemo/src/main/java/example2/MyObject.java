package example2;

/**
 * Created by zeal on 2017/7/3.
 */

public class MyObject {

    public synchronized void method() {
        try {
            System.out.println("bengin method threadName = " + Thread.currentThread().getName());

            Thread.sleep(5000);

            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
