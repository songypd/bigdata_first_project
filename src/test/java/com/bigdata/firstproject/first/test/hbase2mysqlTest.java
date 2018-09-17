package com.bigdata.firstproject.first.test;

import com.bigdata.hbase2mysql.service.HBase2Mysql;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author yuanpeng.song
 * @create 2018/9/13
 * @since 1.0.0
 */
public class hbase2mysqlTest extends BaseTest{
    @Autowired
    HBase2Mysql hBase2Mysql;
    @Test
    public void test() throws IOException{
        hBase2Mysql.execute();
    }
}