package com.niuparser.yazhiwebapi.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

public class RpcJsonClientTest {

	public static void main(String[] args) throws XmlRpcException {
		String str="腾讯公司在2015年推出了王者荣耀的对战游戏";
		try {
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("http://47.92.244.54:8000"));
			XmlRpcClient client = new XmlRpcClient();
			client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
			client.setConfig(config);
			Object[] params = new Object[1];
			params[0] = str;
			String result = (String) client.execute("pos", params);
			System.out.println("result："+result);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
