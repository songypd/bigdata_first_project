package com.bigdata.hbase2mysql.service;

import com.bigdata.common.ConstantEnums;
import com.bigdata.hbase2mysql.dao.BeikeResBjMapper;
import com.bigdata.hbase2mysql.model.BeikeResBj;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuanpeng.song
 * @create 2018/9/13
 * @since 1.0.0
 * 将数据由hbase转到MySQL中去
 */
@Service
public class HBase2Mysql {

    @Autowired
    private BeikeResBjMapper beikeResBjMapper;

    HBaseAdmin admin = null;
    HTable hTable = null;
    String tablename = "log";
    private static final byte[] CF = "cf".getBytes();
    private static final byte[] AVE = "average".getBytes();
    private static final byte[] COUNT = "count".getBytes();

    protected void init() throws Exception {
        Configuration conf = new Configuration(true);
        conf.set("hbase.zookeeper.quorum", "node02,node03,node04");
        admin = new HBaseAdmin(conf);
        hTable = new HTable(conf, tablename);
    }

    public void execute() throws IOException {
        Scan scan = new Scan();
        scan.setStartRow(String.valueOf(1).getBytes());
        scan.setStopRow(String.valueOf(13).getBytes());
        ResultScanner results = hTable.getScanner(scan);
        List<BeikeResBj> res = new LinkedList<>();
        results.forEach(r -> {
            BeikeResBj b = new BeikeResBj();
            b.setAddress(ConstantEnums.getByCode(Integer.parseInt(new String(r.getRow()))).getContent());
            b.setAveragePrice(Double.valueOf(new String(CellUtil.cloneValue(r.getColumnLatestCell(CF, AVE)))));
            b.setTotalCount(new String(CellUtil.cloneValue(r.getColumnLatestCell(CF, COUNT))));
            res.add(b);
        });

        res.forEach(r -> {
//            log.info(r.getAddress());
        });
        beikeResBjMapper.bathInsert(res);
    }

    /**
     * 测试【i；
     * @param res
     */
    private void insertDatas(List<BeikeResBj> res){

    }

    protected void close() throws IOException {
        if (admin != null) {
            admin.close();
        }
        if (hTable != null) {
            hTable.close();
        }
    }

}