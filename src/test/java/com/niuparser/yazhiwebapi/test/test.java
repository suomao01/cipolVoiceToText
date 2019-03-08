package com.niuparser.yazhiwebapi.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {
    public static void main(String[] args) {
        httpURLConnectionPOST("你好中国人们欢迎你");
    }

    public static final String POST_URL = "http://47.92.244.54:9000/?properties={\"annotators\":\"tokenize,ssplit,pos,lemma,ner,parse,depparse,natlog,openie\",\"outputFormat\":\"json\",\"openie.format\":\"ollie\"}";

    public static void httpURLConnectionPOST(String parm) {//parm 你要post的数据
        PrintWriter out = null;
        try {
            URL url = new URL(POST_URL);
            // 将url以open方法返回的urlConnection 连接强转为HttpURLConnection连接
            // (标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接输出流为true,默认false (post请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);
            // 设置连接输入流为true
            connection.setDoInput(true);
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求缓存设为false
            connection.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            // 设置请求头里面的各个属性 (以下为设置内容的类型)
            // application/x-javascript text/xml->xml数据
            // application/x-javascript->json对象
            // application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必须要，不然会出现乱码
            //该样例使用json对象进行请求
            connection.setRequestProperty("Content-Type", "application/x-javascript;charset=utf-8");
            // 建立连接
            connection.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,记得设置参数编码格式
            OutputStreamWriter outWriter =
                    new OutputStreamWriter(connection.getOutputStream(), "utf-8");
            out = new PrintWriter(outWriter);
            // 将参数输出到连接
            out.print(parm);
            // 输出完成后刷新并关闭流
            out.flush();
            out.close();
            System.out.println(connection.getResponseCode());// 不是200的请跳楼
            // 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf =
                    new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据
            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close(); // 日常关流
            connection.disconnect(); // 销毁连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
