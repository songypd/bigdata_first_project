package com.bigdata.firstproject.first.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yuanpeng.song
 * @project first
 * @create 10:52 2018/7/17
 */
public class TestHdfs {
    Configuration conf;
    FileSystem fs;

    @Before
    public void conn() throws IOException {
        conf = new Configuration(true);
        fs = FileSystem.get(conf);
    }

    @After
    public void close() throws IOException {
        fs.close();
    }

    @Test
    public void test001() throws IOException {
        //创建目录
        Path p = new Path("/data");
        if (fs.exists(p)) {
            fs.delete(p, true);
        }
        fs.mkdirs(p);
    }

    @Test
    public void upload() throws IOException {
        //文件上传
        FileInputStream is = new FileInputStream(new File("D:\\BJSXT-BIGDATA\\hadoop-day02.pptx"));
        Path p = new Path("/data/test");
        FSDataOutputStream stream = fs.create(p);
        IOUtils.copyBytes(is, stream, conf, true);
    }

    @Test
    public void download() throws IOException{
        //文件下载
        File file = new File("D:/test/data/test.pptx");
        if (!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream fo = new FileOutputStream(file);
        Path p = new Path("/data/test");
        FSDataInputStream stream = fs.open(p);
        IOUtils.copyBytes(stream,fo,conf);
    }

    @Test
    public void getBLKInfos() throws IOException {
        Path p = new Path("/data/test");
        System.out.println(fs.getDefaultBlockSize(p));
        System.out.println(fs.getDefaultReplication(p));
        FileStatus f = fs.getFileStatus(p);
        BlockLocation[] blks = fs.getFileBlockLocations(f, 0, f.getLen());

        for (BlockLocation b:blks){
            //块的偏移量，块的大小  块的位置信息
            //块的位置信息、计算向数据移动
            //并行度越高，计算速度越快
            System.out.print("==================");
            System.out.print(b);
            System.out.print("==================");
        }
        //seek()---从哪开始读
    }

}
