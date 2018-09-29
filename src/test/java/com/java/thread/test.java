package com.java.thread;

import com.bigdata.common.ConstantEnums;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author yuanpeng.song
 * @create 2018/9/12
 * @since 1.0.0
 */
public class test {
    @Test
    public void test_001() {
        String value = "大兴,黄村北,链家,182㎡,12天前发布,整租 · 顺驰领海 3室2厅 8500元,https://bj.zu.ke.com/zufang/BJ2068810574019698688.html,8500,精装-集中供暖-双卫生间";
        String[] args = value.toString().split(",");
        int i = 1;
        System.out.println(ConstantEnums.getByContent(args[0]).getCode());
        String a = args[3];
        String b = args[7];
        BigDecimal area = new BigDecimal(a.substring(0,a.length()-1));
        BigDecimal price = new BigDecimal(b.substring(0,b.length()));
        BigDecimal average = BigDecimal.ZERO;
        average = price.divide(area,3,BigDecimal.ROUND_HALF_UP);
        System.out.println(area.toString());
        System.out.println(price.toString());
        System.out.println(average);
    }
}