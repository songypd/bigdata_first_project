/**
 * 
 */
package com.bigdata.mapReduce.author;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author yuanpeng.song Create on 2018年10月13日
 */
public class GroupCompartor extends WritableComparator {
	public GroupCompartor() {
		super(Model.class,true);
	}
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		
		Model m1 = (Model)a;
		Model m2 = (Model)b;
		//分组比较器,名称相同，国籍有交集，机构有交集，即为一组数据
		System.out.println(m1.getName()+"======"+m2.getName());
		
		int c1 = m1.compareTo(m2);
		return c1;
	}

}
