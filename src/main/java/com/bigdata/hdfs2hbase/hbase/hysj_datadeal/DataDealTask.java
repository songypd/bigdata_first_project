package com.bigdata.hdfs2hbase.hbase.hysj_datadeal;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author yuanpeng.song
 * @create 2018/9/29
 * @since 1.0.0
 */
public class DataDealTask {
    public static void main(String[]args) throws Exception{
        Configuration conf = new Configuration(true);
        conf.set("fs.defaultFS","hdfs://node01:8020");
        Job job = Job.getInstance(conf);
        job.setJobName("data_res_001");
        job.setJarByClass(DataDealTask.class);
        job.setMapperClass(DataMapper_01.class);
        job.setMapOutputValueClass(Text.class);
        job.setMapOutputKeyClass(IntWritable.class);
//        job.setCombinerClass(DataReduce.class);
        job.setNumReduceTasks(0);
        FileInputFormat.addInputPath(job,new Path("/data/hy_orders.txt"));
        FileInputFormat.setMaxInputSplitSize(job,50*1024*1024);
        FileOutputFormat.setOutputPath(job,new Path("/data/res/04"));
        job.setReducerClass(DataReduce.class);
        job.setNumReduceTasks(1);
        job.waitForCompletion(true);
    }
}