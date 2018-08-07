package com.java.thread;

/**
 * @author yuanpeng.song
 * @project first
 * @create 10:49 2018/8/1
 * 多线程的可见性s
 */
public class Volatile {
    private static volatile boolean isstop= false;
//    private static boolean isstop= false;

    public static void stopit(){
        isstop= true;
    }

    public static void main (String []args){
        new Thread_001().start();
        long t_01 = System.currentTimeMillis();
        while (!isstop){

//            println方法里面加了synchronized同步块，正是因为这个同步块保证了里面变量x的可见性
//            System.out.println("no_stop");
        }
        long t_02= System.currentTimeMillis();
        System.out.println("stop");
        System.out.println("耗时"+(t_02-t_01)+"");

    }

    static class Thread_001 extends Thread{
        @Override
        public void run() {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stopit();
        }
    }
}
