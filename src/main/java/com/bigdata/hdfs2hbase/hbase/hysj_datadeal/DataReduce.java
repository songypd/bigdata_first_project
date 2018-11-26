package com.bigdata.hdfs2hbase.hbase.hysj_datadeal;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yuanpeng.song
 * @create 2018/9/29
 * @since 1.0.0
 */
public class DataReduce extends Reducer<IntWritable,Text,Text,IntWritable> {
//    int sum = 0;
    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Set set = new HashSet<>();
        for (Text t:values){
            set.add(t.toString());
        }
        context.write(new Text("zongshu"),new IntWritable(set.size()));
    }
}