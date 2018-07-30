package com.bigdata.mapReduce.TQ.reduce;

import com.bigdata.mapReduce.TQ.model.TQmodel;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author yuanpeng.song
 * @project first
 * @create 17:41 2018/7/30
 */
public class Partition extends Partitioner<TQmodel,IntWritable> {
    @Override
    public int getPartition(TQmodel model, IntWritable intWritable, int i) {
        return model.getYear() % i;
    }
}
