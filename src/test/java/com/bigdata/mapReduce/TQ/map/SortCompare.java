package com.bigdata.mapReduce.TQ.map;

import com.bigdata.mapReduce.TQ.model.TQmodel;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author yuanpeng.song
 * @project first
 * @create 17:10 2018/7/30
 */
public class SortCompare extends WritableComparator {

    public SortCompare(){
        super(TQmodel.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
//        return super.compare(a, b);
        TQmodel t1 = new TQmodel();
        TQmodel t2 = new TQmodel();
        int c1 = Integer.compare(t1.getYear(),t2.getYear());
        if (c1 == 0){
            int c2 = Integer.compare(t1.getMonth(),t2.getMonth());
            if (c2==0){
                return -Integer.compare(t1.getTemp(),t2.getTemp());
            }
            return c2;
        }
        return c1;
    }
}
