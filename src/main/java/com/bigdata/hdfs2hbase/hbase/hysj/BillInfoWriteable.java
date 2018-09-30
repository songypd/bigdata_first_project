package com.bigdata.hdfs2hbase.hbase.hysj;

import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yuanpeng.song
 * @create 2018/9/29
 * @since 1.0.0
 */
public class BillInfoWriteable implements DBWritable {
    private BillModel billModel;

    public BillInfoWriteable(){

    }

    public BillInfoWriteable (BillModel model){
        this.billModel = model;
    }

    @Override
    public void write(PreparedStatement statement) throws SQLException {
        Date date = new Date(System.currentTimeMillis());
        statement.setString(1, billModel.getUserId());
        statement.setString(2, billModel.getOrderId());
        statement.setString(3, billModel.getBillNumber());
        statement.setDate(4, new Date(billModel.getPlannedRepayDate().getTime()));
        statement.setBigDecimal(5, billModel.getPlannedRepayPrincipal());
        statement.setBigDecimal(6, billModel.getPlannedRepayInterest());
        statement.setBigDecimal(7, billModel.getPlannedRepayAmount());
        statement.setDate(8, new Date(billModel.getRepayDate().getTime()));
        statement.setBigDecimal(9, billModel.getRepayPrincipal());
        statement.setBigDecimal(10, billModel.getRepayInterest());
        statement.setBigDecimal(11, billModel.getRepayAmount());
        statement.setInt(12, billModel.getOverdueDays());
        statement.setDate(13, date);
        statement.setDate(14, date);

    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {

    }
}