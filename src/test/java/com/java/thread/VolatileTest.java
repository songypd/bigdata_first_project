package com.java.thread;

/**
 * @author yuanpeng.song
 * @create 2018/11/9
 * @since 1.0.0
 */
public class VolatileTest {
    public static volatile int race = 0;//类加载过程的准备阶段会给静态常量赋数据类型的零值

//    public int increment(int race){
//        return race++;
//    }

    public static void increment() {
        race++;
    }

    private final static int THREADS_COUNT = 20;

    public static void main(String[] args) {
//        System.out.println(race);
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        System.out.println(Thread.currentThread().getId()+"--"+race);
                        increment();
                    }
                }
            });
            threads[i].start();
        }
        //等待所有累加线程都结束
        while (Thread.activeCount() >1)
            //使用yield()的目的是让相同优先级的线程之间能适当的轮转执行
            Thread.yield();
        System.out.println(race);
    }
}