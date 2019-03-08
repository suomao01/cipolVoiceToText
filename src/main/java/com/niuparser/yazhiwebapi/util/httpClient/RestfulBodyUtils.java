package com.niuparser.yazhiwebapi.util.httpClient;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.niuparser.yazhiwebapi.util.com.JSONChangeUtil;
import com.niuparser.yazhiwebapi.vo.ParamVo;
import com.niuparser.yazhiwebapi.vo.ResultVo;


public class RestfulBodyUtils {
	protected static Logger logger = LoggerFactory.getLogger(RestfulBodyUtils.class);
	
	/**
	 * restFul Json传递
	 * @param paramVo
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static ResultVo postBodyRestFul(ParamVo paramVo,String url) throws IOException{
		 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MediaType type = MediaType.parseMediaType("application/json");
		headers.setContentType(type);
		
		HttpEntity<ParamVo> httpEntity = new HttpEntity<ParamVo>(paramVo, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResultVo> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResultVo.class);
		ResultVo res = (ResultVo)result.getBody();
		
		return res;
	}
	
	/**
	 * main方法
	 * @param args
	 * @throws JsonProcessingException 
	 */
	public static void main(String[] args) throws JsonProcessingException{
		String url = "http://localhost:9002/haNlpWeb/haNlpApi/findClassification";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MediaType type = MediaType.parseMediaType("application/json");
		headers.setContentType(type);
		ParamVo paramVo = new ParamVo();
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		paramVo.setContent("罗纳尔多");
		form.add("paramVo", JSONChangeUtil.objToJson(paramVo));
		//form.add("content", paramVo.getContent());
		logger.info("===============paramVo===================="+JSONChangeUtil.objToJson(paramVo));
//		
		HttpEntity<ParamVo> httpEntity = new HttpEntity<ParamVo>(paramVo, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResultVo> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResultVo.class);
		ResultVo res = (ResultVo)result.getBody();
		logger.info("===============postDataRestFul===================="+JSONChangeUtil.objToJson(res));
	}

	/**
	 * 传递文件
	 * @param file
	 * @param url
	 * @param fileDirPath
	 * @return
	 * @throws IOException
	 */
	public static ResultVo postFileRestFul(MultipartFile file, String url, String fileDirPath) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MediaType type = MediaType.parseMediaType("multipart/form-data");
		headers.setContentType(type);
		String filePath = fileDirPath+file.getOriginalFilename();
		//String filePath = "";
		checkDirIsExists(fileDirPath);
		
		File files = new File(filePath);
		
		if(!files.exists()){
			files.createNewFile();
		}
		// 转存文件  
		file.transferTo(files);
		FileSystemResource resource = new FileSystemResource(files);
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		form.add("file", resource);
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String,Object>>(form, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResultVo> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResultVo.class);
		logger.info("===============postFileRestFul===================="+result);
		ResultVo res = result.getBody();
		return res;
	}
	/**
	 * 目录不存在则创建目录
	 * @param fileDirPath
	 */
	private static void checkDirIsExists(String fileDirPath) {
		File dir = new File(fileDirPath);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}
}
