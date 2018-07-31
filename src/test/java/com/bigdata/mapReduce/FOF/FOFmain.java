package com.bigdata.mapReduce.FOF;

import com.bigdata.mapReduce.FOF.mapper.FOFmapper;
import com.bigdata.mapReduce.FOF.reduce.FOFreduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author yuanpeng.song
 * @project first
 * @create 17:20 2018/7/31
 */
public class FOFmain {

    public static void main(String[] args) throws Exception {
        //conf
        Configuration conf = new Configuration(true);
        //job
        Job job = Job.getInstance(conf);
        job.setJarByClass(FOFmain.class);
        //inputFormat
        Path in_001 = new Path("");
        FileInputFormat.addInputPath(job, in_001);
        //outFormat
        Path out_001 = new Path("");
        if (out_001.getFileSystem(conf).exists(out_001)) {
            out_001.getFileSystem(conf).delete(out_001, true);
        }
        FileOutputFormat.setOutputPath(job,out_001);
        //mapTask
        job.setMapperClass(FOFmapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //reduceTask
        job.setReducerClass(FOFreduce.class);
        //submit  true---打印日志信息
        job.waitForCompletion(true);
    }
}
