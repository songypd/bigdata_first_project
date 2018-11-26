package com.java;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuanpeng.song
 * @create 2018/11/12
 * @since 1.0.0
 */
public class Collections {
    public static void main (String[]args){

        Map testMap = new ConcurrentHashMap();
        testMap.put("01","00000000");
        testMap.put("02","111111111");
        /**
         * putIfAbsent
         * 如果传入key对应的value已经存在，就返回存在的value，不进行替换。
         * 如果不存在，就添加key和value，返回null
         */
        Boolean absent = testMap.putIfAbsent("01","00000000") != null ?true:false;
        System.out.println(absent);
        while (true){

        }
//        sort();
    }

    private static void sort() {
        Integer[] arrays={1,3,2,5,3,7,4,11,33,44,22};
        Arrays.asList(arrays).sort(Integer::compareTo);
//        int a = arrays[-1];
//        System.err.println(a);
        for (Integer i:arrays){
            System.out.println(i);
        }
    }
}