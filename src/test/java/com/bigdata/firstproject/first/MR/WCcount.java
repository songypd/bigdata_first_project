package com.bigdata.firstproject.first.MR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author 宋先森
 * @create 2018/7/22
 * @since 1.0.0
 */
public class WCcount {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration(true);
//        conf.set("mapreduce.app-submission.cross-platform","true");
        Job job = Job.getInstance(conf);
        job.setJarByClass(WCcount.class);
//        job.setInputFormatClass();
        job.setJobName("firstMR");
        job.setMapOutputKeyClass(Text.class);
        FileInputFormat.addInputPath(job, new Path("/data/wordWC.txt"));
        Path out = new Path("/data/result/001");
        if (out.getFileSystem(conf).exists(out)) {
            out.getFileSystem(conf).delete(out, true);
        }
        FileOutputFormat.setOutputPath(job, out);
        job.setMapperClass(WordMapper.class);
        job.setCombinerClass(WordReduce.class);
        job.setNumReduceTasks(1);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(WordReduce.class);
        job.waitForCompletion(true);
    }
}


class WordMapper extends Mapper<Object, Text, Text, IntWritable> {

    private static final IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
//        super.map(key, value, context);
        while (itr.hasMoreTokens()) {
            word.set(itr.nextToken());
            context.write(word, one);
        }
    }
}

class WordReduce extends Reducer<Text,IntWritable,Text,IntWritable> {

    private IntWritable result = new IntWritable();
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//        super.reduce(key, values, context);
        int sum = 0;
        for (IntWritable val:values){
            System.out.print(val);
            System.out.print(":");
            System.out.println(key);

            sum +=val.get();
        }
        result.set(sum);
        context.write(key,result);

        Iterator<IntWritable> it = values.iterator();
        while (it.hasNext()){
            IntWritable val = it.next();
        }

    }
}