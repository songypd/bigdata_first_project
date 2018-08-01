package com.bigdata.mapReduce.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * @author 宋先森
 * @create 2018/7/22
 * @since 1.0.0
 */
public class WrodCount {
    Configuration conf;
    FileSystem fs;
    @Before
    public void conn()throws IOException{
        conf = new Configuration(true);
        fs = FileSystem.get(conf);
    }
    @After
    public void close() throws IOException{
        fs.close();
    }
}