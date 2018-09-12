package com.bigdata.hdfs2hbase.test.demoWc;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author yuanpeng.song
 * @create 2018/9/12
 * @since 1.0.0
 */
public class wcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] args = value.toString().split(" ");
//        Arrays.stream(args).forEach(e->{
//            context.write(new Text(e),new IntWritable(1));
//        });
        for (String e : args) {
            context.write(new Text(e), new IntWritable(1));
        }
    }
}