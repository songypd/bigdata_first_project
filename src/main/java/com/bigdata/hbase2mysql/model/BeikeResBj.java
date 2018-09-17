package com.bigdata.hbase2mysql.model;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeikeResBj implements Writable, DBWritable {
    private Integer id;

    private String address;

    private Double averagePrice;

    private String totalCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount == null ? null : totalCount.trim();
    }


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.address);
        out.writeDouble(this.averagePrice);
        out.writeUTF(this.totalCount);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.address= in.readUTF();
        this.averagePrice= in.readDouble();
        this.totalCount = in.readUTF();
    }

    @Override
    public void write(PreparedStatement statement) throws SQLException {
        statement.setString(1,address);
        statement.setDouble(2,averagePrice);
        statement.setString(3,totalCount);
    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        address = resultSet.getString(1);
        totalCount= resultSet.getString(3);
        averagePrice= resultSet.getDouble(2);

    }
}