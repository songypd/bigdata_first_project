package com.reflect;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author yuanpeng.song
 * @create 13:58 2018/8/24
 */
public class ReflectMain {

    @Test
    public void mainTest() throws Exception{
        String a = "i love ";
        Object[] arr = {a};
        Class clazz = Class.forName("com.reflect.ReflectTest001");
        clazz.getMethod("test_001",String.class).invoke(clazz.newInstance(),a);
//        Method m = clazz.getMethod("test_001",String.class).invoke(arr);
//        System.out.println(m.invoke(arr));

    }
}

class ReflectTest001 {


    public void test_001(String append) {
        String name = "money";
        System.out.println(append+name);

    }


}