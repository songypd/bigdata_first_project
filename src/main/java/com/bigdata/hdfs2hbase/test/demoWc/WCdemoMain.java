package com.bigdata.hdfs2hbase.test.demoWc;

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
public class WCdemoMain {
    public static void  main(String[]args) throws Exception{
        Configuration conf = new Configuration(true);
        conf.set("fs.defaultFS","hdfs://node01:8020");
        conf.set("hbase.zookeeper.quorum","node02,node03,node04");
        Job job = Job.getInstance(conf);
        job.setMapperClass(wcMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job,new Path("/data/"));
        TableMapReduceUtil.initTableReducerJob("wc",wcReducer.class,job,null,null,null,null,false);
        job.waitForCompletion(true);
    }
}