package com.java.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author yuanpeng.song
 * @project first
 * @create 13:47 2018/8/1
 * 控制线程等待，直到倒计时器归0再继续执行
 */
public class CountDownLatch_01 {

    public static void main(String[] args) throws InterruptedException{
        CountDownLatch cdl = new CountDownLatch(5);//约定倒计时的数量，在这也是是线程的数量

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        cdl.countDown();
                        System.out.println(cdl.getCount());
//                        System.out.println(ThreadLocal.class.);
                    }
                };
            }.start();
        }
        cdl.await();
        System.out.println(cdl.getCount());
        System.out.println(String.format("耗时：%sms",System.currentTimeMillis()-start));

    }

}
