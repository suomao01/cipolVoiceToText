package com.niuparser.yazhiwebapi.util.httpClient;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.niuparser.yazhiwebapi.util.param.ParamJson;

public class HttpRestTemlateUtils {
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @return
	 */
	public String getClient(String url,MultiValueMap<String, Object>  params){
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response1 = template.getForEntity(url,String.class,params);
		String resultStr = response1.getBody();
		return resultStr;
	}

	/**
	 * post请求
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public Object postClient(String url, MultiValueMap<String, Object> paramMap) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response1 = template.postForEntity(url,paramMap,String.class);
		String resultStr = response1.getBody();
		return resultStr;
	}
	
	/**
	 * 参数构建
	 * @param param
	 * @return
	 */
	public MultiValueMap<String, Object> paramBuild(ParamJson param){
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		return paramMap;
	}
}
