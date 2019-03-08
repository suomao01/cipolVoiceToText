package com.niuparser.yazhiwebapi.apiController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.niuparser.yazhiwebapi.common.cache.Cache;
import com.niuparser.yazhiwebapi.common.cache.CacheKit;
import com.niuparser.yazhiwebapi.util.com.JSONChangeUtil;
import com.niuparser.yazhiwebapi.util.com.ZhClauseUtils;
import com.niuparser.yazhiwebapi.util.httpClient.HttpClientWeb;
import com.niuparser.yazhiwebapi.util.httpClient.RestfulFileUtils;
import com.niuparser.yazhiwebapi.util.json.DocChekJson;
import com.niuparser.yazhiwebapi.util.json.FenCiJson;
import com.niuparser.yazhiwebapi.util.json.NerJson;
import com.niuparser.yazhiwebapi.util.json.ZhParserJson;
import com.niuparser.yazhiwebapi.util.param.ParamFile;
import com.niuparser.yazhiwebapi.util.param.ParamJsonData;

@RestController
@RequestMapping("/beiJingShow")
public class BeiJingShowController {
	protected static Logger logger = LoggerFactory.getLogger(BeiJingShowController.class);


	@Value("${file.data.parser.url}")
	private String fileDataUrl;//文档数据获取地址

	@Value("${interface.translate.ner.url}")
	private String nerUrl;//多领域实体识别地址

	@Value("${word.checking.url}")
	private String wordCheckUrl;//word格式检查地址

	@Value("${zh.text.parser.url}")
	private String zhTextParserUrl;//中文句法语义分析

	@Value("${file.dir.path}")
	private String fileDirPath;//文件上传缓存地址
	/**
	 * 文档数据提取
	 * @param paramFile
	 * @return
	 */
	@RequestMapping(value="/fileDataParser",method={RequestMethod.POST})
	public Object fileDataParser(@RequestParam MultipartFile myFile) {
		Map paramMap = new HashMap<>();
		String resStr = "";
		try {
			logger.info("fileDataUrl"+fileDataUrl);
			resStr = RestfulFileUtils.postFileRestFul(myFile, fileDataUrl,fileDirPath);
			logger.info("fileDataParser"+resStr);
			if(StringUtils.isEmpty(resStr)){
				return new FenCiJson();
			}

			FenCiJson jsonRes = (FenCiJson)JSONChangeUtil.jsonToObj(new FenCiJson(), resStr);

			return jsonRes;
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new FenCiJson();
		}
	}

	/**
	 * 文档格式检查
	 * @param paramFile
	 * @return
	 */
	@RequestMapping(value="/docCheck",method={RequestMethod.POST})
	public Object docCheck(@RequestParam MultipartFile myFile){
		String resStr = "";
		DocChekJson re= new  DocChekJson();
		try {
			logger.info("wordCheckUrl"+wordCheckUrl);
			logger.info("fileDirPath"+fileDirPath);
			resStr = RestfulFileUtils.postFileRestFul(myFile, wordCheckUrl,fileDirPath);
			logger.info("docCheck"+resStr);
			if(StringUtils.isEmpty(resStr)){
				re.setState("error");
				return re;
			}
			return JSONChangeUtil.jsonToObj(new DocChekJson(), resStr);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			re.setState("error");
			return re;
		}
	}
	/**
	 * 中文句法语义分析
	 * @param paramFile
	 * @return
	 */
	@RequestMapping(value="/fileNiuParser",method={RequestMethod.POST})
	public Object fileNiuParser(@RequestBody ParamJsonData paramData) {
		String resStr = "";
		ZhParserJson json = new ZhParserJson();
		try {
			logger.info("zhTextParserUrl"+zhTextParserUrl);
			if(paramData != null && !StringUtils.isEmpty(paramData.getData())){
				String data = paramData.getData();
				String[] sentence = ZhClauseUtils.chineseClause(data);
				resStr = RestfulFileUtils.postDataRestFul(sentence, zhTextParserUrl);
				logger.info("fileNiuParser"+resStr);
				if(StringUtils.isEmpty(resStr)){
					json.setState("error");
					json.setText("返回结果为空");
					return json;
				}
				return JSONChangeUtil.jsonToObj(new ZhParserJson(), resStr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			json.setState("error");
			json.setText("系统错误");
			return json;
		}
		json.setState("error");
		json.setText("需要分析的数据为空");
		return json;
	}
	/**
	 * 问答系统
	 * @param paramFile
	 * @return
	 */
	@RequestMapping(value="/kbNiuQA",method={RequestMethod.POST})
	public Object kbNiuQA(@RequestBody ParamFile paramFile) {
		Map paramMap = new HashMap<>();
		String resStr = "";
		try {
			File file = new File("/home/yasmart/yazhi_web_api/doc/20180815文本API.docx");
			paramMap.put("file", file);
			logger.info("fileDataParser paramMap"+paramMap);

			resStr = HttpClientWeb.sendPostDataByJson(fileDataUrl, JSON.toJSONString(paramMap), "utf-8");

			logger.info("fileDataParser"+resStr);
			return resStr;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resStr;
	}
	/**
	 * 多实体命名识别
	 * @param paramData
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping(value="/findNer",method={RequestMethod.POST})
	public Object findNer(@RequestBody ParamJsonData paramData) throws ClientProtocolException, IOException{
		if(paramData ==null){
			CacheKit.put(Cache.CIPOLCACHE, "123456", "ccccccccccccccccccccccccc");
			return CacheKit.get(Cache.CIPOLCACHE, "123456");
		}
		logger.info("nerUrl"+nerUrl);
		String resStr = HttpClientWeb.sendPostDataByJson(nerUrl, JSON.toJSONString(paramData), "utf-8");
		logger.info("findNer"+resStr);
		if(StringUtils.isEmpty(resStr)){
			return new NerJson();
		}
		NerJson jsonRes = (NerJson)JSONChangeUtil.jsonToObj(new NerJson(), resStr);
		return jsonRes;
	}

	@RequestMapping(value="/findCache",method={RequestMethod.POST})
	public Object findNer() throws ClientProtocolException, IOException{
		CacheKit.put(Cache.CIPOLCACHE, "123456", "ccccccccccccccccccccccccc");
		return CacheKit.get(Cache.CIPOLCACHE, "123456");
	}
}
