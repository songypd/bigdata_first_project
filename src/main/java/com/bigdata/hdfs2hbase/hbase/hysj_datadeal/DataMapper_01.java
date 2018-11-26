package com.bigdata.hdfs2hbase.hbase.hysj_datadeal;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author yuanpeng.song
 * @create 2018/9/29
 * @since 1.0.0
 */
public class DataMapper_01 extends Mapper<LongWritable,Text,IntWritable,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strs = value.toString().split(",");
        if (strs[8].equals("-")){
            return;
        }
        //       2017,20560647,罗秋菊,210*****41,692,10000.0,367553560,36,1500,0,月,6,2017-11-02,2018-05-01,等额本息,1500,52.92,108.48,0,0
//        context.write(new Text(strs[1]),new IntWritable(1));

        context.write(new IntWritable(1),new Text(strs[1]));
    }
}