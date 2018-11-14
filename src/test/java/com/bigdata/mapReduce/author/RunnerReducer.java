/**
 * 
 */
package com.bigdata.mapReduce.author;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yuanpeng.song Create on 2018年10月13日
 */
public class RunnerReducer extends Reducer<Model, IntWritable, Text, IntWritable> {

	Text keyOut = new Text();
	IntWritable val = new IntWritable();

	@Override
	protected void reduce(Model key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		// 原语；相同的KEY为一组，这一组数据调起一次reduce任务，再任务里面对这组数据进行迭代处理
		// 此处相同的key为，名字相同，国籍有交集，部门有交集，符合上述三个条件即为一组数据
		// 对val进行累加即可获取最终结果

		keyOut.set(key.getName());
		int count = 0;
		for (IntWritable v : values) {
			count += v.get();
		}
		val.set(count);
		context.write(keyOut, val);

	}

}
