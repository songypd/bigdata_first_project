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
    public static void main(String[]args){
        String source = "\\xE6\\x9C\\x9D\\xE9\\x98\\xB3,\\xE5\\x8A\\xB2\\xE6\\x9D\\xBE,\\xE6\\xB6\\xA6\\xE9\\x82\\xA6\\xE5\\x9C\\xB0\\xE4\\xBA\\xA7,12\\xE3\\x8E\\xA1,1\\xE4\\xB8\\xAA\\xE6\\x9C\\x88\\xE5\\x89\\x8D\\xE5\\x8F\\x91\\xE5\\xB8\\x83";
        String[] arg = source.split(",");
        String res = "";
        for (String e:arg){
            res = res+","+hexStr2Str(e);
        }
        System.out.println(res);
    }
}