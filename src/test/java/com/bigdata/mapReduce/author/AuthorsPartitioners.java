/**
 * 
 */
package com.bigdata.mapReduce.author;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author yuanpeng.song
 * Create on 2018年10月14日     
 */
public class AuthorsPartitioners extends Partitioner<Model,IntWritable>{

	
	@Override
	public int getPartition(Model model, IntWritable intWritable, int reducerNums) {
		//按照作者名做分区
		return (model.getName().hashCode() & Integer.MAX_VALUE) % reducerNums;
	}

}
