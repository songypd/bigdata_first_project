package com.bigdata.hbase2mysql.MR;

import com.bigdata.hbase2mysql.model.BeikeResBj;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;

import java.io.IOException;

/**
 * @author yuanpeng.song
 * @create 2018/9/17
 * @since 1.0.0
 */
public class MyMapper extends TableMapper<BeikeResBj, BeikeResBj> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        BeikeResBj model = new BeikeResBj();
        int code = Integer.parseInt((new String(value.getRow(), "UTF-8")));
//        model.setAddress(ConstantEnums.getByCode(code).getContent());
        model.setAddress(code+"");
        int count = 0;
        for (Cell cell : value.listCells()) {
            String colValue = new String(CellUtil.cloneValue(cell), "UTF-8");
            if (count == 0) {
                model.setAveragePrice(Double.valueOf(colValue));
                count++;
            }else {
                model.setTotalCount(colValue);
            }
        }
        context.write(model, model);

    }
}