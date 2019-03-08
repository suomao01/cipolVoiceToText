package com.niuparser.yazhiwebapi.apiController;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.niuparser.yazhiwebapi.util.com.JSONChangeUtil;
import com.niuparser.yazhiwebapi.util.httpClient.HttpClientWeb;
import com.niuparser.yazhiwebapi.util.json.FenCiJson;
import com.niuparser.yazhiwebapi.util.json.NerJson;
import com.niuparser.yazhiwebapi.util.param.ParamJsonData;

@RestController
@RequestMapping("/translate")
public class TranslateController {
	protected static Logger logger = LoggerFactory.getLogger(TranslateController.class);
	
	
	@Value("${interface.translate.fenci.url}")
	private String fenUrl;
	
	@Value("${interface.translate.ner.url}")
	private String nerUrl;
	
	@RequestMapping(value="/findWordseg",method={RequestMethod.POST})
	public Object findWordseg(@RequestBody ParamJsonData paramData) throws ClientProtocolException, IOException{
		if(paramData != null){
			logger.info("findWordseg-param{"+paramData.getData()+"}");
		}
		String resStr = HttpClientWeb.sendPostDataByJson(fenUrl, JSON.toJSONString(paramData), "utf-8");
		logger.info("findWordseg"+resStr);
		if(StringUtils.isEmpty(resStr)){
			return new FenCiJson();
		}
		FenCiJson jsonRes = (FenCiJson)JSONChangeUtil.jsonToObj(new FenCiJson(), resStr);
		/*if(jsonRes != null){
			List<String> list = jsonRes.getResult();
			for(String str:list){
				//logger.info("strOld:"+str);
				str = UnicodeString.unicodeToString(str);
				//logger.info("str:"+str);
			}
		}*/
		return jsonRes;
	}
	
	@RequestMapping(value="/findNer",method={RequestMethod.POST})
	public Object findNer(@RequestBody ParamJsonData paramData) throws ClientProtocolException, IOException{
		if(paramData != null){
			logger.info("findNer-param{"+paramData.getData()+"}");
		}
		
		String resStr = HttpClientWeb.sendPostDataByJson(nerUrl, JSON.toJSONString(paramData), "utf-8");
		logger.info("findNer"+resStr);
		if(StringUtils.isEmpty(resStr)){
			return new NerJson();
		}
		NerJson jsonRes = (NerJson)JSONChangeUtil.jsonToObj(new NerJson(), resStr);
		/*if(jsonRes != null){
			List<String[]> list = jsonRes.getResult();
			for(String[] str:list){
				//logger.info("strOld:"+str);
				String oldStr = str[0];
				String newStr = UnicodeString.unicodeToString(oldStr);
				str[0] = newStr;
				//logger.info("str:"+str);
			}
		}*/
		return jsonRes;
	}

}
