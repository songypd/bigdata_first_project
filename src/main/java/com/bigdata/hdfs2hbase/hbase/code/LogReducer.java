package com.bigdata.hdfs2hbase.hbase.code;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author yuanpeng.song
 * @create 2018/9/12
 * @since 1.0.0
 */
public class LogReducer extends TableReducer<IntWritable, Text, ImmutableBytesWritable> {
    private static final byte[] CF = "cf".getBytes();
    private static final byte[] AVERAGE = "average".getBytes();
    private static final byte[] COUNT = "count".getBytes();


    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Put put = new Put(key.toString().getBytes());
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal area = BigDecimal.ZERO;
        int count = 0;
        for (Text t : values) {
//            String str = new String(t.getBytes());
            String[] args = (new String(t.getBytes())).split("#");
            String a = args[0];
            String b = args[1];
            try {
                price = price.add(new BigDecimal(b));
            } catch (Exception e) {
                e.printStackTrace();
            }
            area = area.add(new BigDecimal(a.substring(0, a.length() - 1)));
            count++;
        }
        BigDecimal average = price.divide(area, 3, BigDecimal.ROUND_HALF_UP);
        put.add(CF, AVERAGE, average.toString().getBytes());
        put.add(CF, COUNT, String.valueOf(count).getBytes());
        System.err.println(average);
        context.write(null,put);
    }
}