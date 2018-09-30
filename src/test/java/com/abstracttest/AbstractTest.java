package com.abstracttest;

/**
 * @author yuanpeng.song
 * @create 10:35 2018/8/29
 */
public abstract class AbstractTest {
    public void before(){
        System.out.println("before");
    }

    public void  execute(){
        before();
        work();
        after();
    }

    public abstract void work();

    public void after(){
        System.out.println("after");
    }
}

class AbstractTestImpl extends AbstractTest{
    @Override
    public void work() {
        System.out.println("work");
    }
}

class test{
    public static void main(String[]args){
        AbstractTestImpl impl = new AbstractTestImpl();
        impl.execute();
    }
}