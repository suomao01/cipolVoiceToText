package com.niuparser.yazhiwebapi.util;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 调用
 */
public class apiUtils {

    public static String getNlpResult(String type, String str) {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        String result = "";
        try {
            config.setServerURL(new URL("http://47.92.244.54:8000"));
            XmlRpcClient client = new XmlRpcClient();
            client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
            client.setConfig(config);
            Object[] params = new Object[1];
            params[0] = str;
             result = (String) client.execute(type, params);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return result;
    }

}
