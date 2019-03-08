package com.niuparser.yazhiwebapi.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpRpcTest {

	public static void main(String[] args) throws IOException {
		String str="你好麻麻";
		String Url = "http://47.92.244.54:8000/pinyin("+str+")";
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//创建httpGet
		HttpPost httpPost=new HttpPost(Url);
		System.out.println("URL is "+httpPost.getURI());
		CloseableHttpResponse response = null;
		try {
			//执行http请求
			response=httpClient.execute(httpPost);
			//获取http响应体
			HttpEntity entity=response.getEntity();
			System.out.println("-----------------");
			//打印响应状态
			System.out.println(response.getStatusLine());
			if(entity!=null) {
				System.out.println("Response Content Length:"+entity.getContentLength());
				String content=EntityUtils.toString(entity);
				System.out.println("Response Content:"+content);
			}
			System.out.println("------------------");
		
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			response.close();
			httpClient.close();
		}
	}

}
