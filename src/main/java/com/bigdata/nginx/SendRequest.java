package com.bigdata.nginx;

/**
 * @author yuanpeng.song
 * @create 2018/9/10
 * @since 1.0.0
 */
public class SendRequest {
    public static void main(String[] args) {
        //发送 GET 请求
        String url = "http://node05:80/test.log";
        int num = 0;
        while (true) {
            if (num > 100) {
                break;
            }
            String s = HttpRequest.sendGet(url, "key=123&v=456");
            num++;

        }
//        System.out.println(s);

        //发送 POST 请求
//        String sr=HttpRequest.sendPost("http://localhost:8080/Home/RequestPostString", "key=123&v=456");
//        System.out.println(sr);
    }
}