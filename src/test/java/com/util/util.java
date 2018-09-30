package com.util;

import org.junit.Test;

/**
 * @author yuanpeng.song
 * @create 17:11 2018/8/24
 */

public class util {

    @Test
    public void transfer() {


        String[] arr = {"TOTAL_FIRST_RECHARGE_USER_COUNT",
                "TOTAL_RECHARGE_USER_COUNT",
                "TOTAL_ITEM_DEADLINE_ALL_WEIGHTED_AVERAGE_MATURITY",
                "TOTAL_ITEM_DEADLINE_LOAN_WEIGHTED_AVERAGE_MATURITY",
                "TOTAL_ITEM_DEADLINE_DEPOSIT_WEIGHTED_AVERAGE_MATURITY",
                "TOTAL_INVEST_LOAN_USER_COUNT",
                "TOTAL_INVEST_ALL_USER_COUNT",
                "TOTAL_INVEST_DEPOSIT_USER_COUNT",
                "TOTAL_PLATFORM_REPEATED_INVEST_ALL_AVERAGE_DEADLINE",
                "TOTAL_PLATFORM_REPEATED_INVEST_LOAN_AVERAGE_DEADLINE",
                "TOTAL_PLATFORM_REPEATED_INVEST_DEPOSIT_AVERAGE_DEADLINE",
                "TOTAL_ITEM_REPEATED_INVEST_LOAN_AVERAGE_DEADLINE",
                "TOTAL_ITEM_REPEATED_INVEST_DEPOSIT_AVERAGE_DEADLINE",
                "TOTAL_PLATFORM_REPEATED_INVEST_ALL_USER_COUNT",
                "TOTAL_PLATFORM_REPEATED_INVEST_LOAN_USER_COUNT",
                "TOTAL_PLATFORM_REPEATED_INVEST_DEPOSIT_USER_COUNT",
                "TOTAL_ITEM_REPEATED_INVEST_LOAN_USER_COUNT",
                "TOTAL_ITEM_REPEATED_INVEST_DEPOSIT_USER_COUNT",
                "TOTAL_PLATFORM_FIRST_INVEST_ALL_AVERAGE_DEADLINE",
                "TOTAL_PLATFORM_FIRST_INVEST_LOAN_AVERAGE_DEADLINE",
                "TOTAL_PLATFORM_FIRST_INVEST_DEPOSIT_AVERAGE_DEADLINE",
                "TOTAL_ITEM_FIRST_INVEST_LOAN_AVERAGE_DEADLINE",
                "TOTAL_ITEM_FIRST_INVEST_DEPOSIT_AVERAGE_DEADLINE"};

        for (String str : arr) {

//            System.out.println("private void "+camelName(str)+"(){}");
            System.out.println(camelName(str)+"()");
        }
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        int count = 0;
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
//                 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
