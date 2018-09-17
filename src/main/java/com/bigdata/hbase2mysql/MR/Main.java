package com.bigdata.hbase2mysql.MR;

import com.bigdata.hbase2mysql.model.BeikeResBj;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;

/**
 * @author yuanpeng.song
 * @create 2018/9/17
 * @since 1.0.0
 */
public class Main {
    public static void main(String[]args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
//        Configuration conf = new Configuration(true);
//        需要注意的是一定要在有连接hbase和mysql的信息之后再初始化job
        conf.set("hbase.zookeeper.quorum", "node02,node03,node04");
        conf.set("fs.defaultFS", "hdfs://node01:8020");
        DBConfiguration.configureDB(
                conf, "com.mysql.jdbc.Driver",
                "jdbc:mysql://node05:3306/data_res",
                "root", "123456");
        String family = "cf";
        String c1 = "count";
        String c2 = "average";
        String tablename = "log";

        Job job = Job.getInstance(conf, "analyze table data for " + tablename);
        job.setJarByClass(Main.class);

        Scan scan = new Scan();
        scan.setCaching(500);
        scan.setCacheBlocks(false);
        scan.setStartRow(String.valueOf(1).getBytes());
        scan.setStopRow(String.valueOf(99).getBytes());//hbaseRowKey是以字典序做排序的，所以1-9是所有的数据
        TableMapReduceUtil.initTableMapperJob(tablename.getBytes(), scan, MyMapper.class, BeikeResBj.class, BeikeResBj.class, job,false);

        DBOutputFormat.setOutput(job, "beike_res_bj", "address,average_price,total_count");
        job.setNumReduceTasks(0);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}