package com.niuparser.yazhiwebapi.apiController;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.niuparser.yazhiwebapi.util.com.JSONChangeUtil;
import com.niuparser.yazhiwebapi.util.json.FenCiJson;
import com.niuparser.yazhiwebapi.util.json.cipolParser.CipolParserJson;
import com.niuparser.yazhiwebapi.util.param.ParamCipolJson;

/**
 * 思拓演示(依存分析)
 *
 * @author 87951
 */
@RestController
@RequestMapping("/cipolShow/dep")
public class NlpCipolShowDepController {
    protected static Logger logger = LoggerFactory.getLogger(NlpCipolShowDepController.class);


    @Value("${nlp.show.cipol.path}")
    private String nlpCipolPath;//思拓演示接口地址

    /**
     * 句法依存分析
     *
     * @return
     */
    @RequestMapping(value = "/parse", method = {RequestMethod.POST})
    public Object seg(@RequestParam("text") String text) {
        Map resMap = new HashMap<>();
        String resStr = "";
        try {
        	if(StringUtils.isBlank(text)){
        		resMap.put("code",400);//参数错误
        		return resMap;
        	}
        	String str = text;
        	//设置请求链接信息
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

            // 创建httpclient对象
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            // 设置请求参数
            StringEntity stringEntity = new StringEntity(str, ContentType.create("multipart/form-data", Charset.forName("UTF-8")));
            stringEntity.setContentEncoding("utf-8");
            httpPost.setEntity(stringEntity);

            // 执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = httpClient.execute(httpPost);

            // 获取结果实体
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }else{
            	resMap.put("code",500);//系统错误
            	return resMap;
            }
            // 释放链接
            response.close();
            if(StringUtils.isNotBlank(result)){
            	CipolParserJson mapJson = (CipolParserJson)JSONChangeUtil.jsonToObj(new CipolParserJson(),result);
            	resMap.put("code",200);//正常返回
            	resMap.put("mapJson", mapJson);
            	return resMap;
            }else{
            	resMap.put("code",410);//返回参数为空
            	return resMap;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resMap.put("code",500);//系统错误
        	return resMap;
        }
    }
}
