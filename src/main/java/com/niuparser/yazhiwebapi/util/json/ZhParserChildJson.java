package com.niuparser.yazhiwebapi.util.json;

public class ZhParserChildJson {

	private String sentence;//原句
	
	private String seg;//分词
	
	private String pos;//词性
	
	private String dep;//依存

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getSeg() {
		return seg;
	}

	public void setSeg(String seg) {
		this.seg = seg;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}
	
	
}
