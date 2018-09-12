package com.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yuanpeng.song
 * @create 2018/9/11
 * @since 1.0.0
 */
public class HbaseDemo_001 {
    HBaseAdmin admin = null;
    String tablename = "test";
    String columnFamily = "test_001";
    HTable hTable = null;

    //初始化admin
    @Before
    public void init() throws Exception {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "node02,node03,node04");
        admin = new HBaseAdmin(conf);
        hTable = new HTable(conf, tablename);
    }

    //创建表
    @Test
    public void createTable() throws Exception {
        //判断表是否存在
        if (admin.tableExists(TableName.valueOf(tablename))) {
            admin.disableTable(TableName.valueOf(tablename));
            admin.disableTable(TableName.valueOf(tablename));
        }
        //创建表的描述信息
        HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tablename));
        //创建列族
        HColumnDescriptor family = new HColumnDescriptor("cf");
        //列族属性设置；最大版本数、最小版本数
        //读缓存
        family.setBlockCacheEnabled(true);
        family.setMaxVersions(3);
        family.setMinVersions(1);
        //有效时间
        family.setTimeToLive(Integer.MAX_VALUE);
        //将列族信息添加到表描述中去
        desc.addFamily(family);
        //创建表admin
        admin.createTable(desc);
    }

    //插入内容--单条多次
    @Test
    public void insert() throws Exception {
        int rowkey = 1;
        Put put = null;
        while (rowkey < 30) {
            put = new Put(String.valueOf(rowkey).getBytes());
            //增加内容
            put.add("cf".getBytes(), "name".getBytes(), ("test_00" + rowkey).getBytes());
            put.add("cf".getBytes(), "age".getBytes(), (rowkey + "").getBytes());
            hTable.put(put);
            rowkey++;
        }
    }

    //批量插入   put(List<Put> puts)
    //查询内容
    @Test
    public void get() throws Exception {
        Get get = new Get("1".getBytes());
        get.addColumn("cf".getBytes(), "age".getBytes());
        Result res = hTable.get(get);
        Cell cell = res.getColumnLatestCell("cf".getBytes(), "age".getBytes());
        System.err.println(new String(CellUtil.cloneValue(cell)));
//        System.err.println(new String(CellUtil.cloneValue(cell.g)));
    }


    //批量查询
    @Test
    public void scan() throws Exception{
        Scan scan = new Scan();
        scan.setStartRow(String.valueOf(1).getBytes());
        scan.setStopRow(String.valueOf(20).getBytes());
        ResultScanner scanner = hTable.getScanner(scan);
        System.out.println("==========================================================");
        scanner.forEach(e->{
            System.err.println(new String(CellUtil.cloneValue(e.getColumnLatestCell("cf".getBytes(),"name".getBytes()))));
            System.err.println(new String(CellUtil.cloneValue(e.getColumnLatestCell("cf".getBytes(),"age".getBytes()))));
        });
        scanner.close();
        System.out.println("==========================================================");
    }

    //删除表 先禁用-》再删除
    //首先disable，然后在执行drop
    @Test
    public void drop() throws Exception {
        if (admin.tableExists(TableName.valueOf(tablename))) {
            if (admin.isTableAvailable(tablename)) {
                admin.disableTable(tablename);
            }
            admin.deleteTable(tablename);

        }
    }

    //结束任务
    @After
    public void close() throws Exception {
        if (admin != null) {
            admin.close();
        }
        if (hTable != null) {
            hTable.close();
        }

    }
}