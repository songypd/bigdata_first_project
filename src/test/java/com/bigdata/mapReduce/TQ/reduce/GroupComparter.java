package com.bigdata.mapReduce.TQ.reduce;

import com.bigdata.mapReduce.TQ.model.TQmodel;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author yuanpeng.song
 * @project first
 * @create 17:42 2018/7/30
 */
public class GroupComparter extends WritableComparator {
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TQmodel t1 = new TQmodel();
        TQmodel t2 = new TQmodel();
        int c1 = Integer.compare(t1.getYear(), t2.getYear());
        if (c1 == 0) {
            return Integer.compare(t1.getMonth(), t2.getMonth());
        }
        return c1;
    }
}
