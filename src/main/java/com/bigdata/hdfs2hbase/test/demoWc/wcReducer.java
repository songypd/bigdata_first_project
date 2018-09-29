package com.bigdata.hdfs2hbase.test.demoWc;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * @author yuanpeng.song
 * @create 2018/9/12
 * @since 1.0.0
 */
public class wcReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable> {

    public static final byte[] CF = "cf".getBytes();
    public static final byte[] COUNT = "count".getBytes();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        Put put = new Put(key.toString().getBytes());
        int count = 0;
        for (IntWritable i : values) {
            count += i.get();
        }
        put.add(CF,COUNT,(count+"").getBytes());
        context.write(null,put);
    }
}