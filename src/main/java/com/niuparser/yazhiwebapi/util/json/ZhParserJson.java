package com.niuparser.yazhiwebapi.util.json;

import java.util.List;

public class ZhParserJson {
	
	private String state;//状态
	
	private List<ZhParserChildJson> info;//分析结果

	private String text;//信息

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<ZhParserChildJson> getInfo() {
		return info;
	}

	public void setInfo(List<ZhParserChildJson> info) {
		this.info = info;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
