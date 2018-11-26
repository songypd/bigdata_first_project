/**
 * 
 */
package com.bigdata.mapReduce.author;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author yuanpeng.song Create on 2018年10月13日
 */
public class Runner {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration(true);
		Job job = Job.getInstance(conf);
		conf.set("fs.defaultFS", "hdfs://node01:8020");
		job.setJarByClass(Runner.class);
		job.setJobName("AuthorsCount");
		job.setMapOutputKeyClass(Model.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setMapperClass(RunnerMapper.class);
		job.setCombinerClass(RunnerReducer.class);
		job.setReducerClass(RunnerReducer.class);
		job.setPartitionerClass(AuthorsPartitioners.class);
		job.setGroupingComparatorClass(GroupCompartor.class);
		job.setSortComparatorClass(SortCompartor.class);
		FileInputFormat.addInputPath(job, new Path("/data/test.txt"));
		FileOutputFormat.setOutputPath(job, new Path("/data/res/authors/"));
		job.setNumReduceTasks(3);
		job.waitForCompletion(true);

	}
}
