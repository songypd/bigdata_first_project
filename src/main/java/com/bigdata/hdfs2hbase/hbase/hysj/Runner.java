package com.bigdata.hdfs2hbase.hbase.hysj;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

/**
 * @author yuanpeng.song
 * @create 2018/9/29
 * @since 1.0.0
 */
public class Runner {
    public static void main(String[]args) throws Exception{
        Configuration conf = new Configuration(true);
        conf.set("fs.defaultFS","hdfs://node01:8020");
        DBConfiguration.configureDB(conf, "com.mysql.jdbc.Driver","jdbc:mysql://node05:3306/data_res","root", "123456");
        Job job = Job.getInstance(conf);
        job.setJobName("demo");
        job.setJarByClass(Runner.class);
        job.setMapperClass(DataMapper.class);
        job.setMapOutputValueClass(BillInfoWriteable.class);
        job.setMapOutputKeyClass(BillInfoWriteable.class);
//        job.setPartitionerClass(Partitions.class);
        job.setNumReduceTasks(0);
        FileInputFormat.addInputPath(job,new Path("/data/hy_bills.txt"));
        FileInputFormat.setMaxInputSplitSize(job,50*1024*1024);
        DBOutputFormat.setOutput(job, "bill",  "user_id", "order_id", "bill_number","planned_repay_date","planned_repay_principal","planned_repay_interest","planned_repay_amount","repay_date","repay_principal","repay_interest","repay_amount","overdue_days","create_time","update_time");
        job.setOutputFormatClass(DBOutputFormat.class);
        job.waitForCompletion(true);
    }
}