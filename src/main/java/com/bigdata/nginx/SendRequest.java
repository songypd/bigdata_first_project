package com.bigdata.nginx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuanpeng.song
 * @create 2018/9/10
 * @since 1.0.0
 */
public class SendRequest {

    public static void main(String[] args) throws Exception {
        //发送 GET 请求
        String url = "http://node05:80/test.log";
        int num = 0;
        List<String> params = getParams();
        params.forEach(e -> {
            String s = HttpRequest.sendGet(url, e.toString());
            System.err.println(e.toString());
        });
        num++;

        //发送 POST 请求
//        String sr=HttpRequest.sendPost("http://localhost:8080/Home/RequestPostString", "key=123&v=456");
//        System.out.println(sr);
    }


    private static List<String> getParams() throws Exception {
        InputStreamReader isr;
        FileInputStream fis;
        BufferedReader br;
        File f_01 = new File("D:\\test\\data\\beike_zz.txt");
        File f_02 = new File("D:\\test\\data\\beike_bj.csv");
        fis = new FileInputStream(f_01);
        isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);
        List<String> params = new LinkedList<>();
        String str;
        int count = 0;
        boolean f_01_end = false;
        while ((str = br.readLine()) != null) {
            if (count == 0) {
                count++;
                continue;
            }
//            params.add(str);

        }
//        f_01_end = true;
        str = null;
        count = 0;
        fis = new FileInputStream(f_02);
        isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);
        while ((str = br.readLine()) != null) {
            if (count == 0) {
                count++;
                continue;
            }
            params.add(str);
        }
        return params;
    }
}