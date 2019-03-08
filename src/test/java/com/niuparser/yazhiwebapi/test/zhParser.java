package com.niuparser.yazhiwebapi.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zhParser {

	public static void main(String[] args) {
		String str = "第一句。第二句！第三句：第四句；第五句。";     
	    
		/*正则表达式：句子结束符*/    
		String regEx="：|。|！|；";      
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
		    
		/*输出结果*/    
		for(int index = 0; index < words.length; index++)     
		{     
		    String word = words[index];     
		    System.out.println("word = " + word);     
		} 

	}

}
