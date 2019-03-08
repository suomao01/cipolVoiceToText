package com.niuparser.yazhiwebapi.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class testStr2json {

	public static void main(String[] args) {
		String str = "(ROOT\r\n  (IP\r\n    (NP (NR 乔治布什))\r\n    (VP (VV 出生)\r\n      (PP (P 在)\r\n        (NP (NR 德克萨斯州))))\r\n    (PU 。)))";
		str = str.replace("\r\n", "");
		//str = str.replaceAll(" ","");
		System.out.println(str);
		System.out.println(treeify(str.trim().split(" "),0));
		// Map r_map = extractMessageByRegular(str,0,new HashMap());
		// System.out.println(r_map.toString());
	}

	public static String treeify(String[] ptree, int start) {
		System.out.println(start);
		int tpos = start;
		System.out.println(ptree.length);
		for(int i=tpos;i<ptree.length;i++){
			if(!" ".equals(ptree[tpos])){
				tpos++;
			}
		}
		tpos = tpos-2;
		String tag = ptree[start] + "," + ptree[tpos+1];
		String text = "";
		List<String> children = new ArrayList<>();
		if (!"(".equals(ptree[tpos + 1])) {
			int dpos = tpos + 1;
			while (dpos<ptree.length && !")".equals(ptree[dpos])) {
				dpos++;
			}
			text = ptree[tpos + 1] + "," + ptree[dpos-1];
			System.out.println(Node(tag, text, children).toString());
			return  Node(tag, text, children).toString();
		} else {
			int pos = tpos + 1;
			while (!")".equals(ptree[pos])) {
				String cnode = treeify(ptree, pos);
				pos += cnode.length() + 1;
				if (" ".equals(ptree[pos])) {
					pos += 1;
				}
				children.add(cnode);
			}
			if (" ".equals(ptree[pos + 1])) {
				pos += 2;
			}
			System.out.println(Node(tag, text, children).toString());
			return Node(tag, text, children).toString();
		}

	}

	private static Map Node(String tag, String text, List<String> children) {
		Map map = new HashMap<>();
		map.put("tag",tag);
		map.put("text",text);
		map.put("children",children);
		int temp = tag.length() + text.length() + 2;
		if (children.size() != 0) {
			for (int i = 0; i < children.size(); i++) {
				temp += children.get(i).length() + 1;
			}
			temp += children.size() - 1;
		}
		map.put("size",temp);
		return map;
	}

	/**
	 * 使用正则表达式提取中括号中的内容
	 * 
	 * @param msg
	 * @param map
	 * @return
	 */
	public static Map extractMessageByRegular(String msg, int n, Map map) {

		int start = 0;
		n = n + 1;
		System.out.println(n);
		System.out.println(msg);
		if (StringUtils.isBlank(msg)) {
			return map;
		}
		if (msg.startsWith("(")) {
			int e = msg.substring(1).indexOf("(");
			int f = msg.indexOf(")");
			if (f > e && e >= 0) {// 非一组括号
				map.put("name", msg.substring(1, e + 1));
				map.put("children", extractMessageByRegular(msg.substring(e + 1), n, new HashMap()));
			} else {// 一组括号
				map.put("name", msg.substring(1, f));
				map.put("children", extractMessageByRegular(msg.substring(f), n, new HashMap()));
			}
		}
		if (msg.startsWith(")")) {
			extractMessageByRegular(msg.substring(1), n, new HashMap());
		}
		return map;
	}

}
