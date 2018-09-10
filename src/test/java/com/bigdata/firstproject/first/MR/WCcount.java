package com.bigdata.firstproject.first.MR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author 宋先森
 * @create 2018/7/22
 * @since 1.0.0
 */
public class WCcount {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration(true);
        Job job = Job.getInstance(conf);
        job.setJarByClass(WCcount.class);
//        job.setInputFormatClass();
        job.setJobName("firstMR");
        job.setMapOutputKeyClass(Text.class);
        FileInputFormat.addInputPath(job, new Path("/usr/root/test/wordDemo.txt"));
        Path out = new Path("/data/result/001");
        if (out.getFileSystem(conf).exists(out)) {
            out.getFileSystem(conf).delete(out, true);
        }
        FileOutputFormat.setOutputPath(job, out);
        job.setMapperClass(MyWordMapper.class);
        job.setCombinerClass(MyWordReduce.class);
        job.setNumReduceTasks(1);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(MyWordReduce.class);
        job.waitForCompletion(true);
    }
}