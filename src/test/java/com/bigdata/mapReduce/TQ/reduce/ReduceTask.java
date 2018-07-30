package com.bigdata.mapReduce.TQ.reduce;

import com.bigdata.mapReduce.TQ.model.TQmodel;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yuanpeng.song
 * @project first
 * @create 17:42 2018/7/30
 */
public class ReduceTask extends Reducer<TQmodel, IntWritable, Text, IntWritable> {

    Text rkey = new Text();
    IntWritable rval =  new IntWritable();

    @Override
    protected void reduce(TQmodel key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //原语；相同的KEY为一组，这一组数据调起一次reduce任务，再任务里面对这组数据进行迭代处理
        //年月相同的数据
        //取第一第二个数据，即为每个月最高温的数据
        //年,月相同的数据
        //1970,01,03,88
        int start = 0;
        int end = 0;
        for (IntWritable val:values) {
            if (start == 0){
                rkey.set(key.getYear()+""+key.getMonth()+""+key.getDay());
                rval.set(key.getTemp());
                context.write(rkey,rval);
                start++;
                end=key.getDay();
            }
            if (end !=key.getDay() && start!=0){
                rkey.set(key.getYear()+""+key.getMonth()+""+key.getDay());
                rval.set(key.getTemp());
                context.write(rkey,rval);
                break;
            }
        }

    }
}
