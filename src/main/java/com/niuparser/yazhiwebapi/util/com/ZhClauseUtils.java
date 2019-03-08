package com.niuparser.yazhiwebapi.util.com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhClauseUtils {

	/**
	 * 中文断句解析
	 * @param str
	 * @return
	 */
	public static String[] chineseClause(String str ) {
		/*正则表达式：句子结束符*/    
		String regEx="：|。|！|；|？";      
		Pattern p =Pattern.compile(regEx);     
		Matcher m = p.matcher(str);     
		    
		/*按照句子结束符分割句子*/    
		String[] words = p.split(str);     
		    
		/*将句子结束符连接到相应的句子后*/    
		if(words.length > 0)     
		{     
		    int count = 0;     
		    while(count < words.length)     
		    {     
		        if(m.find())     
		        {     
		            words[count] += m.group();     
		        }     
		        count++;     
		    }     
		}    
		return words;
	}
}
