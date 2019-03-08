package com.niuparser.yazhiwebapi.apiController;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ctc.wstx.util.StringUtil;
import com.niuparser.yazhiwebapi.common.cache.Cache;
import com.niuparser.yazhiwebapi.common.cache.CacheKit;
import com.niuparser.yazhiwebapi.util.apiUtils;
import com.niuparser.yazhiwebapi.vo.ResultVo;
import com.niuparser.yazhiwebapi.vo.TranslationDto;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.niuparser.yazhiwebapi.util.com.JSONChangeUtil;
import com.niuparser.yazhiwebapi.util.httpClient.RestfulFileUtils;
import com.niuparser.yazhiwebapi.util.json.FenCiJson;
import com.niuparser.yazhiwebapi.util.param.ParamCipolJson;

/**
 * 思拓演示
 *
 * @author 87951
 */
@RestController
@RequestMapping("/cipolShow")
public class NlpCipolShowController {
    protected static Logger logger = LoggerFactory.getLogger(NlpCipolShowController.class);


    @Value("${nlp.show.cipol.path}")
    private String nlpCipolPath;//思拓演示接口地址
    @Value("${url.translation}")
    private String translationService;
    @Value("${url.languageRecognition}")
    private String languageRecognitionService;

    /**
     * 拼音，纠错
     *
     * @param paramCipol
     * @return
     */
    @RequestMapping(value = "/nlpResult", method = {RequestMethod.POST})
    public Object seg(@RequestBody ParamCipolJson paramCipol) {
        String str = paramCipol.getText();//判空
        Map paramMap = new HashMap<>();
        String resStr = "";
        if (StringUtils.isEmpty(str)) {
            paramMap.put("code", "参数为空");
            return paramMap;
        } else {
            try {
                Object Obj = CacheKit.get(Cache.CIPOLCACHE, str);
                if (Obj != null) {
                    return Obj;
                }
                String correctResult = apiUtils.getNlpResult("correct", str);
                String pinyinResult = apiUtils.getNlpResult("pinyin", correctResult);
                logger.info("纠错处理结果--" + correctResult);
                logger.info("拼音处理结果--" + pinyinResult);
                paramMap.put("correct", correctResult);
                paramMap.put("pinyin", pinyinResult);
                paramMap.put("state", "success");
                CacheKit.put(Cache.CIPOLCACHE, str, paramMap);
                return paramMap;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                paramMap.put("code", 500);
                return paramMap;//修改返回前台错误
            }
        }
    }

    /**
     * 文本分类
     *
     * @param paramCipol
     * @return
     */
    @RequestMapping(value = "/clf", method = {RequestMethod.POST})
    public Object clf(@RequestBody ParamCipolJson paramCipol) {
        String clfStr = paramCipol.getText();
        Map paramMap = new HashMap<>();
        String resStr = "";
        if (StringUtils.isEmpty(clfStr)) {
            paramMap.put("code", "参数为空");
            return paramMap;
        } else {
            try {
                Object Obj = CacheKit.get(Cache.CIPOLCACHE, clfStr + "clf");
                if (Obj != null) {
                    return Obj;
                }
                logger.info("nlpCipolPath:" + nlpCipolPath);
                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
                config.setServerURL(new URL("http://47.92.244.54:8001"));
                XmlRpcClient client = new XmlRpcClient();
                client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
                client.setConfig(config);
                Object[] params = new Object[1];
                params[0] = clfStr;
                String result = (String) client.execute("clf", params);
                logger.info("处理结果--" + result);
                paramMap.put("clf", result);
                paramMap.put("state", "success");
                CacheKit.put(Cache.CIPOLCACHE, clfStr + "clf", paramMap);
                return paramMap;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                paramMap.put("code", 500);
                return paramMap;
            }
        }
    }

    /**
     * 拼音
     *
     * @param paramCipol
     * @return
     */
//    @RequestMapping(value = "/pinyin", method = {RequestMethod.POST})
//    public Object pinyin(@RequestBody ParamCipolJson paramCipol) {
//        String str = paramCipol.getText();
//        Map paramMap = new HashMap<>();
//        String resStr = "";
//        if (StringUtils.isEmpty(str)) {
//            paramMap.put("code", "参数为空");
//            return paramMap;
//        } else {
//            try {
//                logger.info("nlpCipolPath:" + nlpCipolPath);
//                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
//                config.setServerURL(new URL("http://47.92.244.54:8000"));
//                XmlRpcClient client = new XmlRpcClient();
//                client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
//                client.setConfig(config);
//                Object[] params = new Object[1];
//                params[0] = str;
//                String result = (String) client.execute("pinyin", params);
//                logger.info("处理结果--" + result);
//                paramMap.put("pinyin", result);
//                paramMap.put("state", "success");
//                return paramMap;
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return new FenCiJson();
//            }
//        }
//    }

    /**
     * 文本纠错
     *
     * @param paramCipol
     * @return
     */
//    @RequestMapping(value = "/correct", method = {RequestMethod.POST})
//    public Object pincorrectyin(@RequestBody ParamCipolJson paramCipol) {
//        String str = paramCipol.getText();
//        Map paramMap = new HashMap<>();
//        String resStr = "";
//        if (StringUtils.isEmpty(str)) {
//            paramMap.put("code", "参数为空");
//            return paramMap;
//        } else {
//            try {
//                logger.info("nlpCipolPath:" + nlpCipolPath);
//                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
//                config.setServerURL(new URL("http://47.92.244.54:8000"));
//                XmlRpcClient client = new XmlRpcClient();
//                client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
//                client.setConfig(config);
//                Object[] params = new Object[1];
//                params[0] = str;
//                String result = (String) client.execute("correct", params);
//                logger.info("处理结果--" + result);
//                paramMap.put("correct", result);
//                paramMap.put("state", "success");
//                return paramMap;
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return new FenCiJson();
//            }
//        }
//    }

    /**
     * 词性标注
     *
     * @param paramCipol
     * @return
     */
    @RequestMapping(value = "/pos", method = {RequestMethod.POST})
    public Object pos(@RequestBody ParamCipolJson paramCipol) {
        String posStr = paramCipol.getText();
        Map paramMap = new HashMap<>();
        String resStr = "";
        if (StringUtils.isEmpty(posStr)) {
            paramMap.put("code", "参数为空");
            return paramMap;
        } else {
            try {
                Object Obj = CacheKit.get(Cache.CIPOLCACHE, posStr + "pos");
                if (Obj != null) {
                    return Obj;
                }
                logger.info("nlpCipolPath:" + nlpCipolPath);
                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
                config.setServerURL(new URL("http://47.92.244.54:8000"));
                XmlRpcClient client = new XmlRpcClient();
                client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
                client.setConfig(config);
                Object[] params = new Object[1];
                params[0] = posStr;
                String result = (String) client.execute("pos", params);
                logger.info("处理结果--" + result);
                paramMap.put("pos", result);
                paramMap.put("state", "success");
                CacheKit.put(Cache.CIPOLCACHE, posStr + "pos", paramMap);
                return paramMap;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                paramMap.put("code", 500);
                return paramMap;
            }
        }
    }


    /**
     * 情感分析
     *
     * @param paramCipol
     * @return
     */
    @RequestMapping(value = "/sentiment", method = {RequestMethod.POST})
    public Object sentiment(@RequestBody ParamCipolJson paramCipol) {
        String sentimentStr = paramCipol.getText();
        Map paramMap = new HashMap<>();
        String resStr = "";
        if (StringUtils.isEmpty(sentimentStr)) {
            paramMap.put("code", "参数为空");
            return paramMap;
        } else {
            try {
                Object Obj = CacheKit.get(Cache.CIPOLCACHE, sentimentStr + "sentiment");
                if (Obj != null) {
                    return Obj;
                }
                logger.info("nlpCipolPath:" + nlpCipolPath);
                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
                config.setServerURL(new URL("http://47.92.244.54:8000"));
                XmlRpcClient client = new XmlRpcClient();
                client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
                client.setConfig(config);
                Object[] params = new Object[1];
                params[0] = sentimentStr;
                String result = (String) client.execute("sentiment", params);
                logger.info("处理结果--" + result);
                paramMap.put("sentiment", result);
                paramMap.put("state", "success");
                CacheKit.put(Cache.CIPOLCACHE, sentimentStr + "sentiment", paramMap);
                return paramMap;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                paramMap.put("code", 500);
                return paramMap;
            }
        }
    }

    /**
     * 关键字提取
     *
     * @param paramCipol
     * @return
     */
    @RequestMapping(value = "/keywords", method = {RequestMethod.POST})
    public Object keywords(@RequestBody ParamCipolJson paramCipol) {
        String keywordsStr = paramCipol.getText();
        Map paramMap = new HashMap<>();
        String resStr = "";
        if (StringUtils.isEmpty(keywordsStr)) {
            paramMap.put("code", "参数为空");
            return paramMap;
        } else {
            try {
                Object Obj = CacheKit.get(Cache.CIPOLCACHE, keywordsStr + "keywords");
                if (Obj != null) {
                    return Obj;
                }
                logger.info("nlpCipolPath:" + nlpCipolPath);
                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
                config.setServerURL(new URL("http://47.92.244.54:8000"));
                XmlRpcClient client = new XmlRpcClient();
                client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
                client.setConfig(config);
                Object[] params = new Object[1];
                params[0] = keywordsStr;
                String result = (String) client.execute("keywords", params);
                logger.info("处理结果--" + result);
                paramMap.put("keywords", result);
                paramMap.put("state", "success");
                CacheKit.put(Cache.CIPOLCACHE, keywordsStr + "keywords", paramMap);
                return paramMap;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                paramMap.put("code", 500);
                return paramMap;
            }
        }
    }

    @PostMapping(value = "/trans")
    public ResultVo translation(@RequestParam("original") String original) {
        try {
            String recog = original.length() > 50 ? original.substring(0, 50) : original;
            TranslationDto translationDto = executePost(languageRecognitionService, recog, TranslationDto.class);
            if (StringUtils.isEmpty(translationDto.getLanguage())) {
                throw new Exception();
            }
            if ("zh".equals(translationDto.getLanguage())) {
                return new ResultVo(200, original);
            }
            String transUrl = translationService + translationDto.getLanguage() + "&src_text=" + URLEncoder.encode(original, "utf-8");
            TranslationDto dto = executePost(transUrl, TranslationDto.class);
            if (!StringUtils.isEmpty(dto.getError_code())) {
                throw new Exception();
            }
            return new ResultVo(200, dto.getTgt_text().substring(0, dto.getTgt_text().length() - 2));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo(400, "翻译失败");
        }
    }

    private <T> T executePost(String url, Class<T> tClass) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String result;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            return JSONObject.parseObject(result, tClass);
        } else {
            throw new Exception();
        }
    }

    private <T> T executePost(String url, String original, Class<T> tClass) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        ArrayList params = new ArrayList();
        params.add(new BasicNameValuePair("src_text", original));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String result;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            return JSONObject.parseObject(result, tClass);
        } else {
            throw new Exception();
        }
    }


}
