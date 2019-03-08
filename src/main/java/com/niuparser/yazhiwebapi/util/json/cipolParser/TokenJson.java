package com.niuparser.yazhiwebapi.util.json.cipolParser;

public class TokenJson {

	private Integer index;
	
	private String word;
	
	private String originalText;
	
	private String lemma;
	
	private Integer characterOffsetBegin;
	
	private Integer characterOffsetEnd;
	
	private String pos;
	
	private String ner;
	
	private String normalizedNER;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public String getLemma() {
		return lemma;
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
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

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
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
