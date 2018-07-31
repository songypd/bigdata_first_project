package com.bigdata.mapReduce.FOF.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yuanpeng.song
 * @project first
 * @create 18:03 2018/7/31
 */
public class FOFreduce extends Reducer<Text, IntWritable, Text, IntWritable> {

    Text rkey = new Text();
    IntWritable rval = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //原语；相同的key为一组，一组数据调用一次reduce，这一组数据在这个task内迭代
        //liming:wangning 1
        //liming:wangrong 0
        int sum = 0;
        int flag = -1;
        for (IntWritable val : values) {
            sum += val.get();
            if (val.get() == 0) {
                flag = 0;
            }
            if (flag != 0) {
                rkey.set(key);
                rval.set(sum);
                context.write(rkey, rval);
            }
        }

    }
}
