package com.java8;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yuanpeng.song
 * @project first
 * @create 10:36 2018/8/2
 */
public class Lambda_001 {
    @Test
    public void test001(){
       List<Integer> scanList = new LinkedList<>();
       scanList.add(1);
       scanList.add(2);
       scanList.add(3);
       scanList.add(4);
       scanList.add(5);
       scanList.add(6);
       scanList.add(7);
       scanList.add(8);
       scanList.add(9);
       scanList.add(0);
       int n = 0;
        scanList.forEach(e->System.out.print(e+"-----------"));
        System.out.println(scanList.stream().filter(e -> e ==1).count());
        scanList.parallelStream().forEach(e->System.out.print(e+"-----------"));
    }
}
