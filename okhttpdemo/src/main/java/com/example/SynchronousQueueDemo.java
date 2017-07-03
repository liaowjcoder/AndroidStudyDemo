package com.example;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by liaowj on 2017/7/3.
 */

public class SynchronousQueueDemo {
    //true：表示内部是队列的形式

    public static void main(String[] args) throws Exception {

        SynchronousQueue<String> queue = new SynchronousQueue<>(false);

//        queue.put("hello");
//        queue.put("hello1");
//        queue.put("hello2");
//        queue.put("hello3");
//        queue.put("hello4");

//        System.out.println(queue.peek());
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());


//        Producer producer = new Producer(queue);
//        Consumer consumer = new Consumer(queue);
//
//        consumer.start();
//        producer.start();


        boolean offer = queue.offer("hello");
        System.out.println(offer);//false

        String poll = queue.poll();
        System.out.println(poll);//null


    }

    static class Producer extends Thread {
        private SynchronousQueue<String> queue;
        public Producer(SynchronousQueue<String> queue){
            this.queue =queue;
        }
        @Override
        public void run() {
            super.run();
            try {
                queue.put("hello");
//                queue.put("hello2");
//                queue.put("hello3");
//                queue.put("hello4");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Consumer extends Thread {
        private SynchronousQueue<String> queue;
        public Consumer(SynchronousQueue<String> queue){
            this.queue =queue;
        }
        @Override
        public void run() {
            super.run();
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
