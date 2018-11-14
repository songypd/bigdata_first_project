/**
 * 
 */
package com.bigdata.mapReduce.author;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author yuanpeng.song
 * Create on 2018年10月13日     
 */
public class SortCompartor extends WritableComparator{

	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		
		return super.compare(a, b);
	}
	
	
}
