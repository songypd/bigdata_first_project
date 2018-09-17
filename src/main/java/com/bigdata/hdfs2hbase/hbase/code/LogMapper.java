package com.bigdata.hdfs2hbase.hbase.code;

import com.bigdata.common.ConstantEnums;
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
public class LogMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        朝阳,国展,森林地产,30?,24天前发布,合租 ? 左家庄南里 3室1厅,https://bj.zu.ke.com/zufang/BJ2060598350234779648.html,3800,独立卫生间-集中供暖-免中介费
        String[]args = value.toString().split(",");
        if (args[0].equals("address")){
            return;
        }
        if (args.length != 9){
            //存在脏数据
            System.err.println(value.toString());
            return;
        }
        int code_key = ConstantEnums.getByContent(args[0]).getCode();
        String val= args[3]+"#"+args[7]+"#";
        context.write(new IntWritable(code_key),new Text(val));

    }
}