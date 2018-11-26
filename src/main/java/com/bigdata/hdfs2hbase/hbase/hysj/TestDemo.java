package com.bigdata.hdfs2hbase.hbase.hysj;

import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * @author yuanpeng.song
 * @create 2018/9/29
 * @since 1.0.0
 */
public class TestDemo {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    public void test_001(){
        String str = "2017,18455679,347575175,2017101812104097712172641,2017-11-17,1000,8.33,21.67,2017-11-15,1000.0,8.33,21.67,0";
        String[] strs = str.toString().split(",");
        try {
            System.err.println(strs[4]);
            sdf.parse(strs[4]);
            System.err.println(strs[8]);
            sdf.parse(strs[8]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}