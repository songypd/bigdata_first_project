package com.bigdata.hdfs2hbase.hbase.hysj;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * @author yuanpeng.song
 * @create 2018/9/29
 * @since 1.0.0
 */
public class DataMapper extends Mapper<LongWritable, Text, BillInfoWriteable, BillInfoWriteable> {
    private BillModel billModel = new BillModel();
    private BillInfoWriteable billInfoWriteable = null;


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        2017,18455679,347575175,2017101812104097712172641,2017-11-17,1000,8.33,21.67,2017-11-15,1000.0,8.33,21.67,0
        String[] strs = value.toString().split(",");
        if (strs[2].equals("-")) {
            return;
        }
        billModel.setUserId(strs[1]);
        billModel.setOrderId(strs[2]);
        billModel.setBillNumber(strs[3]);
        try {
            if (!strs[4].equals("-")) {
                billModel.setPlannedRepayDate(sdf.parse(strs[4]));
            }
            if (!strs[8].equals("-")) {
                billModel.setRepayDate(sdf.parse(strs[8]));
            }
//            System.err.println(strs[8]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        billModel.setPlannedRepayPrincipal(new BigDecimal(strs[5]));
        billModel.setPlannedRepayInterest(new BigDecimal(strs[6]));
        billModel.setPlannedRepayAmount(new BigDecimal(strs[7]));
        billModel.setRepayPrincipal(new BigDecimal(strs[9]));
        billModel.setRepayInterest(new BigDecimal(strs[10]));
        billModel.setRepayAmount(new BigDecimal(strs[11]));
        billModel.setOverdueDays(Integer.parseInt(strs[12]));

        context.write(new BillInfoWriteable(billModel), null);
    }
}