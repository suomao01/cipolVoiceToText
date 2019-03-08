package com.niuparser.yazhiwebapi.test;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.niuparser.yazhiwebapi.util.com.JSONChangeUtil;
import com.niuparser.yazhiwebapi.util.json.cipolParser.CipolParserJson;

public class test9000 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		Map<String,String> map = new HashMap<>();
		map.put("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,natlog,openie");
		map.put("outputFormat", "json");
		map.put("openie.format", "ollie");
		String propertiesStr = JSONChangeUtil.objToJson(map);
		System.out.println(propertiesStr);
		String url = "http://47.92.244.54:9000/?";
		url = url+URLEncoder.encode("properties="+propertiesStr,"utf-8");
		System.out.println(url);
		String result = "";
		String str = "乔治布什出生在德克萨斯州,是上一轮美国总统。";
        
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        
        // 装填参数
//        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//        nameValuePairs.add(new BasicNameValuePair("str","乔治布什出生在德克萨斯州。"));
        // 设置参数到请求对象中
       // httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
        
        StringEntity stringEntity = new StringEntity(str, ContentType.create("multipart/form-data", Charset.forName("UTF-8")));
        stringEntity.setContentEncoding("utf-8");
        httpPost.setEntity(stringEntity);

        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();
        System.out.println("result:"+result);
        CipolParserJson mapJson = (CipolParserJson)JSONChangeUtil.jsonToObj(new CipolParserJson(),result);
        System.out.println("resultMap:"+mapJson.toString());

	}

}
