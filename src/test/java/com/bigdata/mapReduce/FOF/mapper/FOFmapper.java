package com.bigdata.mapReduce.FOF.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author yuanpeng.song
 * @project first
 * @create 17:30 2018/7/31
 */
public class FOFmapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text mkey = new Text();
    IntWritable mval = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //tom hello hadoop cat
        String[] realtions = StringUtils.split(value.toString(), " ");
        //直接关系、间接关系

        for (int i = 0; i < realtions.length; i++) {
            mkey.set(distinct(realtions[0], realtions[i]));
            mval.set(0);
            //直接关系
            context.write(mkey, mval);
            for (int j = i + 1; j < realtions.length; j++) {
                //间接关系
                mkey.set(distinct(realtions[i], realtions[j]));
                mval.set(1);
                context.write(mkey, mval);
            }
        }

    }

    private String distinct(String s1, String s2) {
        if (s1.compareTo(s2) > 0) {
            return s1 + ":" + s2;
        } else {
            return s2 + ":" + s1;
        }
    }
}
