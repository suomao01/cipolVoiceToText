package com.niuparser.yazhiwebapi.apiController;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niuparser.yazhiwebapi.util.com.JSONChangeUtil;
import com.niuparser.yazhiwebapi.util.httpClient.HttpClientWeb;
import com.niuparser.yazhiwebapi.util.json.TextAutoJson;
import com.niuparser.yazhiwebapi.util.result.ResultCodeNlp;
import com.niuparser.yazhiwebapi.vo.ParamVo;
import com.niuparser.yazhiwebapi.vo.ResultVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pyhtonApi")
public class YaSmartPyhtonApiController {
	protected static Logger logger = LoggerFactory.getLogger(YaSmartPyhtonApiController.class);
	
	@Value("${pyhton.medicine.kbqa.path}")
	private String medicineKbqaPath;
	
	@Value("${pyhton.text.auto.check.path}")
	private String textAutoCheckPath;
	
	@ApiOperation("医药领域问答服务接口")
	@PostMapping(value = "/m_kbqa")
    public Object medicineKbqa(@RequestBody ParamVo paramVo) {
    	ResultVo resultVo = new ResultVo();
    	logger.info("medicineKbqa 问题："+paramVo.getContent());
    	try{
	    	String question = paramVo.getContent();
	    	if(StringUtils.isBlank(question)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	String resultBody = HttpClientWeb.sendGetData(medicineKbqaPath+question, "utf-8");
	    	logger.info("medicineKbqa 答案："+resultBody);
	    	resultVo.setResult(resultBody);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
    }
	/**
	 * 文本句子别字纠错
	 * @param paramVo
	 * @return
	 */
	@ApiOperation("文本句子别字纠错服务接口")
	@PostMapping(value = "/textAutoCheck")
    public Object textAutoCheck(@RequestBody ParamVo paramVo) {
    	ResultVo resultVo = new ResultVo();
    	logger.info("textAutoCheck 问题："+paramVo.getContent());
    	try{
	    	String question = paramVo.getContent();
	    	if(StringUtils.isBlank(question)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	String resultBody = HttpClientWeb.sendGetData(textAutoCheckPath+question, "utf-8");
	    	logger.info("textAutoCheck 答案："+resultBody);
	    	TextAutoJson textAutoJson = new TextAutoJson();
	    	if(StringUtils.isNotBlank(resultBody)){
	    		textAutoJson = (TextAutoJson) JSONChangeUtil.jsonToObj(new TextAutoJson(), resultBody);
	    	}
	    	resultVo.setResult(textAutoJson);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
    }
}
