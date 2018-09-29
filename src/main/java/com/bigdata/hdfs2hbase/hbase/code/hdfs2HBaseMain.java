package com.bigdata.hdfs2hbase.hbase.code;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

/**
 * @author yuanpeng.song
 * @create 2018/9/12
 * @since 1.0.0
 */
public class hdfs2HBaseMain {
    public static void main(String[]args)throws Exception{
        Configuration conf = new Configuration(true);
        conf.set("fs.defaultFS","hdfs://node02:8020");
        conf.set("hbase.zookeeper.quorum","node02,node03,node04");
        Job job = Job.getInstance(conf);
        job.setJarByClass(hdfs2HBaseMain.class);
        job.setMapperClass(LogMapper.class);
        job.setMapOutputValueClass(Text.class);
        job.setMapOutputKeyClass(IntWritable.class);
        FileInputFormat.addInputPath(job,new Path("/flume/2018-09-12/17/"));
        TableMapReduceUtil.initTableReducerJob("log",LogReducer.class,job,null,null,null,null,false);
        job.waitForCompletion(true);
    }
}