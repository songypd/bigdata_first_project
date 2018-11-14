/**
 * 
 */
package com.bigdata.mapReduce.author;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author yuanpeng.song
 * Create on 2018年10月13日     
 */
public class RunnerMapper extends Mapper<LongWritable, Text, Model, IntWritable>{
	
	Model m = new Model();
	IntWritable val = new IntWritable();
	
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
//		 23031488.0$$$$Daniel Souery$$$$ 19804.0$$$$Université Libre de Bruxelles ## Psy Pluriel Centre Européen de Psychologie Médicale$$$$Belgium ## Belgium$$$$药学、制药工业$$$$no elements!$$$$(pubyear-2016)-Pharmacological treatment strategies in unipolar depression in European tertiary psychiatric treatment centers – A pharmacoepidemiological cross-sectional multicenter study$$$$European Neuropsychopharmacology$$$$Elsevier$$$$Bruxelles ## Bruxelles$$$$no elements!$$$$no elements!$$$$no elements!$$$$no elements!$$$$no elements!$$$$ 4889513.0
		String[] datas = value.toString().split("\\u0024\\u0024\\u0024\\u0024");
		//对model进行赋值，并放到缓存冲，等待排序溢写
		m.setName(datas[1]);
		m.setCountry(datas[4]);
		m.setBrands(datas[3]);
		val.set(1);
		context.write(m, val);
	}
	

}
