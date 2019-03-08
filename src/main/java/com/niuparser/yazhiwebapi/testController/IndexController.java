package com.niuparser.yazhiwebapi.testController;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niuparser.yazhiwebapi.entity.YzTSNews;
import com.niuparser.yazhiwebapi.service.YzTSNewsServiceI;
import com.niuparser.yazhiwebapi.util.httpClient.HttpRestTemlateUtils;
import com.niuparser.yazhiwebapi.util.param.ParamJson;
import com.niuparser.yazhiwebapi.util.result.JsonResult;
import com.niuparser.yazhiwebapi.util.result.ResultCode;

@RestController
@RequestMapping("/testApi")
public class IndexController {
	protected static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Value("${interface.fanyi.test.url}")
	private String testUrl;

	
	@Autowired
	private YzTSNewsServiceI yzTSNewsService;

	@RequestMapping(value="/hello",method={RequestMethod.POST})
	public String hello(){
		return "hello spring boot"+testUrl;
	}
	@RequestMapping(value="/findMap/{name}",method={RequestMethod.POST})
	public Map<String,Object> findMap(@PathVariable String name){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("name2", "白云");
		map.put("age", "123");
		return map;
	}
	//method={RequestMethod.POST
	
	@RequestMapping(value="/findN",method={RequestMethod.POST})
	public Object findNew(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name2", "白云");
		map.put("age", "123");
		JsonResult jsonResult = new JsonResult();
		jsonResult.setData(map);
		jsonResult.setMessage(ResultCode.SUCCESS.msg());
		jsonResult.setCode(ResultCode.SUCCESS);
		return jsonResult;
	}
	
	@RequestMapping(value="/findByJson",method={RequestMethod.POST})
	public Object findByJson(@RequestBody ParamJson param){
		JsonResult jsonResult = new JsonResult();
		jsonResult.setData(param);
		jsonResult.setMessage(ResultCode.SUCCESS.msg());
		jsonResult.setCode(ResultCode.SUCCESS);
		return jsonResult;
	}
	
	@RequestMapping(value="/findUrlJson",method={RequestMethod.POST})
	public Object findUrlJson(@RequestBody ParamJson param){
		HttpRestTemlateUtils httpClients = new HttpRestTemlateUtils();
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		paramMap.add("src_text", param.getSrc_text());
		paramMap.add("from", param.getFrom());
		paramMap.add("to", param.getTo());
		//return httpClients.postClient(testUrl,paramMap);
		if("get".equals(param.getMethod())){
			return httpClients.getClient(testUrl,paramMap);
		}else if("post".equals(param.getMethod())){
			return httpClients.postClient(testUrl,paramMap);
		}
		return null;
	}

	
	@RequestMapping(value="/queryOneNews",method={RequestMethod.POST})
	public Object findData(String id) {
		//return httpClients.postClient(testUrl,paramMap);
		YzTSNews bean = yzTSNewsService.getById(id);
		return bean;
	}
	
	

}
