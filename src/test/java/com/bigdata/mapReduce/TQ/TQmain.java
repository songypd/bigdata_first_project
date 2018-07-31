package com.bigdata.mapReduce.TQ;

import com.bigdata.mapReduce.TQ.map.SortCompare;
import com.bigdata.mapReduce.TQ.map.TQMapper;
import com.bigdata.mapReduce.TQ.model.TQmodel;
import com.bigdata.mapReduce.TQ.reduce.GroupComparter;
import com.bigdata.mapReduce.TQ.reduce.Partition;
import com.bigdata.mapReduce.TQ.reduce.ReduceTask;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @author yuanpeng.song
 * @project first
 * @create 14:27 2018/7/30
 */
public class TQmain {
    public static void main (String[]args) throws Exception {
        //配置文件
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //运行的class
        job.setJarByClass(TQmain.class);
        //输入 输出
        Path p1 = new Path("");
        Path p2 = new Path("");
        FileInputFormat.addInputPath(job,p1);
        FileInputFormat.addInputPath(job,p2);
        //输入格式出  输出格式化
        Path p3 = new Path("/data/result/tq");
        if (p3.getFileSystem(conf).exists(p3)){
            p3.getFileSystem(conf).delete(p3,true);
        }
        FileOutputFormat.setOutputPath(job,p3);
        //map
        job.setMapperClass(TQMapper.class);
        job.setMapOutputKeyClass(TQmodel.class);
        job.setMapOutputValueClass(IntWritable.class);
        //mapoutputformat  map的缓存
        //排序比较器
        job.setSortComparatorClass(SortCompare.class);
        //combiner
        //shuffer
        //分区器
        job.setPartitionerClass(Partition.class);
        //分组比较器
        job.setGroupingComparatorClass(GroupComparter.class);
        //reduce
        job.setReducerClass(ReduceTask.class);
        //reduce的数量= 分区的个数
        job.setNumReduceTasks(2);
        job.waitForCompletion(true);
    }
}
