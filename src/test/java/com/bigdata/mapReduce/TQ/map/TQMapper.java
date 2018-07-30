package com.bigdata.mapReduce.TQ.map;

import com.bigdata.mapReduce.TQ.model.TQmodel;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yuanpeng.song
 * @project first
 * @create 16:31 2018/7/30
 */
public class TQMapper extends Mapper<LongWritable, Text, TQmodel, IntWritable> {

    TQmodel model = new TQmodel();
    IntWritable val = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1949-10-01 14:21:02	34c
        //对tqmodel进行赋值，并放到缓存中，等待排序溢写
        try {
            String[] args = StringUtils.split(value.toString(), "\t");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm::ss");
            Date date = sdf.parse(args[0]);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            model.setYear(cal.get(Calendar.YEAR));
            model.setMonth(cal.get(Calendar.MONTH));
            model.setDay(cal.get(Calendar.DAY_OF_MONTH));
            model.setTemp(Integer.parseInt(args[1].substring(0,args[1].length()-1)));
            val.set(model.getTemp());
            context.write(model,val);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
