package com.niuparser.yazhiwebapi.util.json.cipolParser;

public class EntitymentionsJson {

	private Integer docTokenBegin;
	
	private Integer docTokenEnd;
	
	private Integer tokenBegin;
	
	private Integer tokenEnd;
	
	private String text;
	
	private Integer characterOffsetBegin;
	
	private Integer characterOffsetEnd;
	
	private String ner;
	
	private String normalizedNER;

	public Integer getDocTokenBegin() {
		return docTokenBegin;
	}

	public void setDocTokenBegin(Integer docTokenBegin) {
		this.docTokenBegin = docTokenBegin;
	}

	public Integer getDocTokenEnd() {
		return docTokenEnd;
	}

	public void setDocTokenEnd(Integer docTokenEnd) {
		this.docTokenEnd = docTokenEnd;
	}

	public Integer getTokenBegin() {
		return tokenBegin;
	}

	public void setTokenBegin(Integer tokenBegin) {
		this.tokenBegin = tokenBegin;
	}

	public Integer getTokenEnd() {
		return tokenEnd;
	}

	public void setTokenEnd(Integer tokenEnd) {
		this.tokenEnd = tokenEnd;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCharacterOffsetBegin() {
		return characterOffsetBegin;
	}

	public void setCharacterOffsetBegin(Integer characterOffsetBegin) {
		this.characterOffsetBegin = characterOffsetBegin;
	}

	public Integer getCharacterOffsetEnd() {
		return characterOffsetEnd;
	}

	public void setCharacterOffsetEnd(Integer characterOffsetEnd) {
		this.characterOffsetEnd = characterOffsetEnd;
	}

	public String getNer() {
		return ner;
	}

	public void setNer(String ner) {
		this.ner = ner;
	}

	public String getNormalizedNER() {
		return normalizedNER;
	}

	public void setNormalizedNER(String normalizedNER) {
		this.normalizedNER = normalizedNER;
	}
	
}
