package com.java.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @author yuanpeng.song
 * @project first
 * @create 14:08 2018/8/1
 */
public class CyclicBarrie_001 {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(5, () ->
                System.out.println("cb ...is ...finsh")
        );
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(){
              public void run(){
                    try {
                        Thread.sleep(1000);
                        cb.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                  System.out.println(String.format("耗时：%sms",System.currentTimeMillis()-start));
                }
            }.start();
        }

    }
}
