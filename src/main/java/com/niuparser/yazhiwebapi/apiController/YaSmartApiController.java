package com.niuparser.yazhiwebapi.apiController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.niuparser.yazhiwebapi.util.com.JSONChangeUtil;
import com.niuparser.yazhiwebapi.util.httpClient.RestfulBodyUtils;
import com.niuparser.yazhiwebapi.util.result.ResultCodeNlp;
import com.niuparser.yazhiwebapi.vo.ParamVo;
import com.niuparser.yazhiwebapi.vo.ResultVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/nlpApi")
public class YaSmartApiController {
	protected static Logger logger = LoggerFactory.getLogger(YaSmartApiController.class);
	
	@Value("${hanlp.service.path}")
	private String nlpServicePath;
	
	@Value("${hanlp.find.key.word.path}")
	private String findKeyWordPath;
	
	@Value("${hanlp.find.summary.path}")
	private String findSummaryPath;
	
	@Value("${hanlp.find.classification.path}")
	private String findClassificationPath;
	
	@Value("${hanlp.un.zip.path}")
	private String unZipPath;
	
	@Value("${hanlp.convert.simplifiedChinese.path}")
	private String convert2SimplifiedChinesePath;
	
	@Value("${hanlp.phrase.extractor.path}")
	private String phraseExtractorPath;
	
	@Value("${hanlp.text.similarity.path}")
	private String textSimilarityPath;
	
	@Value("${hanlp.text.nearest.path}")
	private String textNearestPath;
	
	@Value("${hanlp.chinese.clause.path}")
	private String chineseClausePath;
	
	@Value("${hanlp.chinese.pinyin.path}")
	private String chinesePinyinPath;
	
	@Value("${file.dir.path}")
	private String fileDirPath;//文件上传缓存地址
	
	@Value("${similarity.service.path}")
	private String similarityServicePath;
	
	@Value("${similarity.tendency.path}")
	private String similarityTendencyPath;
	
	@Value("${similarity.tokenizer.path}")
	private String similarityTokenizerPath;
	
	@Value("${similarity.sentence.similarity.path}")
	private String sentenceSimilarityPath;
	
	@Value("${similarity.phrase.similarity.path}")
	private String phraseSimilarityPath;
	
	@Value("${similarity.word.similarity.path}")
	private String wordSimilarityPath;
	/**
	 * 中文关键字摘取
	 * @param paramVo
	 * @return
	 */
	@ApiOperation("中文关键字摘取服务接口")
	@PostMapping(value = "/findKeyWord")
    public Object findKeyWord(@RequestBody ParamVo paramVo) {
    	ResultVo resultVo = new ResultVo();
    	logger.info("findKeyWord 原文："+paramVo.getContent());
    	logger.info("findKeyWord 数量："+paramVo.getSize());
    	try{
	    	String content = paramVo.getContent();
	    	if(StringUtils.isBlank(content)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	Integer size = paramVo.getSize();
	    	if(size == null || size <= 0){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, nlpServicePath+findKeyWordPath);
	    	if(restfulResultVo != null){
	    		logger.info("findKeyWord 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
    }
	
	/**
     * 自动摘要
     * @param content
     * @return
     */
	@ApiOperation("中文自动摘要服务接口")
    @PostMapping(value = "/findSummary")
    public Object findSummary(@RequestBody ParamVo paramVo) {
    	ResultVo resultVo = new ResultVo();
    	logger.info("findSummary 原文："+paramVo.getContent());
    	logger.info("findSummary 数量："+paramVo.getSize());
    	try{
	    	String content = paramVo.getContent();
	    	if(StringUtils.isBlank(content)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	Integer size = paramVo.getSize();
	    	if(size == null || size <= 0){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, nlpServicePath+findSummaryPath);
	    	if(restfulResultVo != null){
	    		logger.info("findSummary 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
    }
    
    /**
     * 主题分类
     * @param content
     * @return
     */
    @ApiOperation("中文文本主题分类服务接口")
    @PostMapping(value = "/findClassification")
    public Object findClassification(@RequestBody ParamVo paramVo){
        ResultVo resultVo = new ResultVo();
    	logger.info("findClassification 原文："+paramVo.getContent());
    	try{
	    	String content = paramVo.getContent();
	    	if(StringUtils.isBlank(content)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, nlpServicePath+findClassificationPath);
	    	if(restfulResultVo != null){
	    		logger.info("findClassification 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(IOException e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
    	
        return resultVo;
    }
    
    /**
     * 压缩包解压
     * @param myFile
     * @return
     */
    @ApiOperation("压缩包解压服务接口")
    @PostMapping(value = "/unZip")
	public Object unZip(@RequestParam MultipartFile myFile){
		Boolean resStr = false;
		ResultVo resultVo = new ResultVo();
		Map<String,Object> map = new HashMap<>();
		try {
			logger.info("unZip fileDirPath"+fileDirPath);
			ResultVo restfulResultVo = RestfulBodyUtils.postFileRestFul(myFile, nlpServicePath+unZipPath,fileDirPath);
	    	if(restfulResultVo != null){
	    		logger.info("findClassification 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
		}
		return resultVo;
	}
    /**
     * 繁体中文转换成简体中文
     * @param content
     * @return
     */
    @ApiOperation("繁体中文转换成简体中文服务接口")
    @PostMapping(value = "/convert2SimplifiedChinese")
	public Object convertToSimplifiedChinese(@RequestBody ParamVo paramVo){
    	ResultVo resultVo = new ResultVo();
    	logger.info("convertToSimplifiedChinese 原文："+paramVo.getContent());
    	try{
	    	String content = paramVo.getContent();
	    	if(StringUtils.isBlank(content)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, nlpServicePath+convert2SimplifiedChinesePath);
	    	if(restfulResultVo != null){
	    		logger.info("convertToSimplifiedChinese 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
	}
    /**
     * 短语识别
     * @param content
     * @return
     */
    @ApiOperation("中文短语识别服务接口")
    @PostMapping(value = "/phraseExtractor")
	public Object phraseExtractor(@RequestBody ParamVo paramVo){
    	ResultVo resultVo = new ResultVo();
    	logger.info("phraseExtractor 原文："+paramVo.getContent());
    	logger.info("phraseExtractor 数量："+paramVo.getSize());
    	try{
	    	String content = paramVo.getContent();
	    	if(StringUtils.isBlank(content)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	Integer size = paramVo.getSize();
	    	if(size == null || size <= 0){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, nlpServicePath+phraseExtractorPath);
	    	if(restfulResultVo != null){
	    		logger.info("phraseExtractor 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
	}
    
    /**
     * 文本相似度計算
     * @param content
     * @return
     */
    @ApiOperation("中文文本相似度計算服务接口")
    @PostMapping(value = "/textSimilarity")
    public Object textSimilarity(@RequestBody ParamVo paramVo) {
    	ResultVo resultVo = new ResultVo();
    	logger.info("textSimilarity 句子1："+paramVo.getContent());
    	logger.info("textSimilarity 句子2："+paramVo.getContent1());
    	try{
	    	String content = paramVo.getContent();
	    	if(StringUtils.isBlank(content)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	String content1 = paramVo.getContent1();
	    	if(StringUtils.isBlank(content1)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, nlpServicePath+textSimilarityPath);
	    	if(restfulResultVo != null){
	    		logger.info("textSimilarity 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
    }
    
    /**
     * 近义词查询
     * @param content
     * @return
     */
    @ApiOperation("中文近义词查询服务接口")
    @PostMapping(value = "/textNearest")
    public Object textNearest(@RequestBody ParamVo paramVo) {
    	ResultVo resultVo = new ResultVo();
    	logger.info("textNearest 原文："+paramVo.getContent());
    	logger.info("textNearest 数量："+paramVo.getSize());
    	try{
	    	String content = paramVo.getContent();
	    	if(StringUtils.isBlank(content)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	Integer size = paramVo.getSize();
	    	if(size == null || size <= 0){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, nlpServicePath+textNearestPath);
	    	if(restfulResultVo != null){
	    		logger.info("textSimilarity 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
    }
    /**
     * 段落分句
     * @param content
     * @return
     */
    @ApiOperation("中文段落分句服务接口")
    @PostMapping(value = "/chineseClause")
    public Object chineseClause(@RequestBody ParamVo paramVo) {
    	ResultVo resultVo = new ResultVo();
    	logger.info("textNearest 原文："+paramVo.getContent());
    	try{
	    	String content = paramVo.getContent();
	    	if(StringUtils.isBlank(content)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, nlpServicePath+chineseClausePath);
	    	if(restfulResultVo != null){
	    		logger.info("textSimilarity 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
    }
	/**
	 * 中文转拼音
	 * @param paramVo
	 * @return
	 */
    @ApiOperation("中文转拼音服务接口")
    @PostMapping(value = "/chinese2Pinyin")
    public Object chinese2Pinyin(@RequestBody ParamVo paramVo) {
    	ResultVo resultVo = new ResultVo();
    	logger.info("chinese2Pinyin 原文："+paramVo.getContent());
    	try{
	    	String content = paramVo.getContent();
	    	if(StringUtils.isBlank(content)){
	    		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
	    		return resultVo;
	    	}
	    	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, nlpServicePath+chinesePinyinPath);
	    	if(restfulResultVo != null){
	    		logger.info("chinese2Pinyin 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
	    	resultVo.setResult(restfulResultVo);
	    	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
    	}catch(Exception e){
    		e.printStackTrace();
    		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
    	}
        return resultVo;
    }
    /************************************similarity 服务接口*********************************************/
    /**
     * 词语情感分析
     * @param paramVo
     * @return
     */
     @ApiOperation("similarity词语情感分析服务接口")
     @PostMapping(value = "/tendency")
     public Object tendency(@RequestBody ParamVo paramVo) {
     	ResultVo resultVo = new ResultVo();
     	try{
     		if(paramVo == null){
         		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
         		return resultVo;
         	}
     		logger.info("tendency 原文："+paramVo.getContent());
         	if(StringUtils.isBlank(paramVo.getContent())){
         		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
         		return resultVo;
         	}
         	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, similarityServicePath+similarityTendencyPath);
	    	if(restfulResultVo != null){
	    		logger.info("tendency 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
         	resultVo.setResult(restfulResultVo);
         	resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
     	}catch(Exception e){
     		e.printStackTrace();
     		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
     	}
         return resultVo;
     }
     
     /**
      * 分词
      * @param paramVo
      * @return
      */
      @ApiOperation("similarity分词服务接口")
      @PostMapping(value = "/tokenizer")
      public Object tokenizer(@RequestBody ParamVo paramVo) {
      	ResultVo resultVo = new ResultVo();
      	resultVo.setStatus(200);
      	try{
      		if(paramVo == null){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	if(StringUtils.isBlank(paramVo.getContent())){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	logger.info("tokenizer 原文："+paramVo.getContent());
          	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, similarityServicePath+similarityTokenizerPath);
	    	if(restfulResultVo != null){
	    		logger.info("tokenizer 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
         	resultVo.setResult(restfulResultVo);
            resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
      	}catch(Exception e){
      		e.printStackTrace();
      		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
      	}
          return resultVo;
      }
      /**
       * 句子相似度计算
       * @param paramVo
       * @return
       */
      @ApiOperation("similarity句子相似度服务接口")
      @PostMapping(value = "/sentenceSimilarity")
      public Object sentenceSimilarity(@RequestBody ParamVo paramVo) {
      	ResultVo resultVo = new ResultVo();
      	resultVo.setStatus(200);
      	try{
      		if(paramVo == null){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	if(StringUtils.isBlank(paramVo.getContent())){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	if(StringUtils.isBlank(paramVo.getContent1())){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	
          	logger.info("sentenceSimilarity 原文1："+paramVo.getContent());
          	logger.info("sentenceSimilarity 原文2："+paramVo.getContent1());
          	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, similarityServicePath+sentenceSimilarityPath);
	    	if(restfulResultVo != null){
	    		logger.info("sentenceSimilarity 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
             resultVo.setResult(restfulResultVo);
             resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
      	}catch(Exception e){
      		e.printStackTrace();
      		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
      	}
          return resultVo;
      }
      /**
       * 短语相似度
       * @param paramVo
       * @return
       */
      @ApiOperation("similarity短语相似度服务接口")
      @PostMapping(value = "/phraseSimilarity")
      public Object phraseSimilarity(@RequestBody ParamVo paramVo) {
      	ResultVo resultVo = new ResultVo();
      	resultVo.setStatus(200);
      	try{
      		if(paramVo == null){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	if(StringUtils.isBlank(paramVo.getContent())){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	if(StringUtils.isBlank(paramVo.getContent1())){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	logger.info("phraseSimilarity 原文1："+paramVo.getContent());
          	logger.info("phraseSimilarity 原文2："+paramVo.getContent1());
          	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, similarityServicePath+phraseSimilarityPath);
	    	if(restfulResultVo != null){
	    		logger.info("phraseSimilarity 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
             resultVo.setResult(restfulResultVo);
             resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
      	}catch(Exception e){
      		e.printStackTrace();
      		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
      	}
          return resultVo;
      }
      /**
       * 词相似度计算
       * @param paramVo
       * @return
       */
      @ApiOperation("similarity词相似度计算服务接口")
      @PostMapping(value = "/wordSimilarity")
      public Object wordSimilarity(@RequestBody ParamVo paramVo) {
      	ResultVo resultVo = new ResultVo();
      	resultVo.setStatus(200);
      	try{
      		if(paramVo == null){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	if(StringUtils.isBlank(paramVo.getContent())){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	if(StringUtils.isBlank(paramVo.getContent1())){
          		resultVo.setStatus(ResultCodeNlp.PARAMS_ERROR.getCode());
          		return resultVo;
          	}
          	
          	logger.info("phraseSimilarity 原文1："+paramVo.getContent());
          	logger.info("phraseSimilarity 原文2："+paramVo.getContent1());
          	ResultVo restfulResultVo = RestfulBodyUtils.postBodyRestFul(paramVo, similarityServicePath+wordSimilarityPath);
	    	if(restfulResultVo != null){
	    		logger.info("phraseSimilarity 结果："+JSONChangeUtil.objToJson(restfulResultVo));
	    	}
             resultVo.setResult(restfulResultVo);
             resultVo.setStatus(ResultCodeNlp.SUCCESS.getCode());
      	}catch(Exception e){
      		e.printStackTrace();
      		resultVo.setStatus(ResultCodeNlp.SYS_ERROR.getCode());
      	}
          return resultVo;
      }
}
