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


public class RestfulFileUtils {
	protected static Logger logger = LoggerFactory.getLogger(RestfulFileUtils.class);
	
	/**
	 * 文件传输
	 * @param file
	 * @param url
	 * @param fileDirPath
	 * @return
	 * @throws IOException
	 */
	public static String postFileRestFul(MultipartFile file,String url,String fileDirPath) throws IOException{
		 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MediaType type = MediaType.parseMediaType("multipart/form-data");
		headers.setContentType(type);
		String filePath = fileDirPath+file.getOriginalFilename();
		//String filePath = "";
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
		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		logger.info("===============postFileRestFul===================="+result);
		String res = result.getBody();
		return res;
	}
	
	public static String postDataRestFul(String[] sentence,String url) throws IOException{
		 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MediaType type = MediaType.parseMediaType("multipart/form-data");
		headers.setContentType(type);
		
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		form.add("sentence", sentence);
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String,Object>>(form, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		logger.info("===============postDataRestFul===================="+result);
		String res = result.getBody();
		return res;
	}
}
