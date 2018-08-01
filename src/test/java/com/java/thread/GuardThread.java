package com.java.thread;

import java.io.IOException;

/**
 * @author yuanpeng.song
 * @project first
 * @create 11:57 2018/8/1
 */
public class GuardThread {

    public static void execute(){
        for (int i=0;i<=100;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    public static void main(String[]args) throws IOException {
        Thread t_01= new Thread(()->execute());
        /*This method must be invoked before the thread is started.*/
        t_01.setDaemon(true);
        t_01.start();

        System.in.read();
    }
}
