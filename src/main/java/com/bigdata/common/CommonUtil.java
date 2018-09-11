package com.bigdata.common;

/**
 * @author yuanpeng.song
 * @create 2018/9/11
 * @since 1.0.0
 */
public class CommonUtil {

    /**
     * 将16进制转为中文
     *
     * @param s
     * @return
     */
    public static String hexStr2Str(String s) {
        s = s.replace("\\x", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            // UTF-16le:Not
            s = new String(baKeyword, "utf-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

}