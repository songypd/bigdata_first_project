package com.bigdata.firstproject.first.MR;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author 宋先森
 * @create 2018/7/22
 * @since 1.0.0
 */
public class MyWordReduce extends Reducer<Text,IntWritable,Text,IntWritable> {

    private IntWritable result = new IntWritable();
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//        super.reduce(key, values, context);
        int sum = 0;
        for (IntWritable val:values){
            System.out.print(val);
            System.out.print(":");
            System.out.println(key);

            sum +=val.get();
        }
        result.set(sum);
        context.write(key,result);

        Iterator<IntWritable> it = values.iterator();
        while (it.hasNext()){
            IntWritable val = it.next();
        }

    }
}