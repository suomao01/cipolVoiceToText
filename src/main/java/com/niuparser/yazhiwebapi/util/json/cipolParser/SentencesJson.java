package com.niuparser.yazhiwebapi.util.json.cipolParser;

import java.util.List;

public class SentencesJson {

	private Integer index;
	
	private String parse;
	
	private List<DependenciesJson> basicDependencies;
	
	private List<DependenciesJson> enhancedDependencies;
	
	private List<DependenciesJson> enhancedPlusPlusDependencies;
	
	private List<OpenieJson> openie;
	
	private List<EntitymentionsJson> entitymentions;
	
	private List<TokenJson> tokens;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getParse() {
		return parse;
	}

	public void setParse(String parse) {
		this.parse = parse;
	}

	public List<DependenciesJson> getBasicDependencies() {
		return basicDependencies;
	}

	public void setBasicDependencies(List<DependenciesJson> basicDependencies) {
		this.basicDependencies = basicDependencies;
	}

	public List<DependenciesJson> getEnhancedDependencies() {
		return enhancedDependencies;
	}

	public void setEnhancedDependencies(List<DependenciesJson> enhancedDependencies) {
		this.enhancedDependencies = enhancedDependencies;
	}

	public List<DependenciesJson> getEnhancedPlusPlusDependencies() {
		return enhancedPlusPlusDependencies;
	}

	public void setEnhancedPlusPlusDependencies(List<DependenciesJson> enhancedPlusPlusDependencies) {
		this.enhancedPlusPlusDependencies = enhancedPlusPlusDependencies;
	}

	public List<OpenieJson> getOpenie() {
		return openie;
	}

	public void setOpenie(List<OpenieJson> openie) {
		this.openie = openie;
	}

	public List<EntitymentionsJson> getEntitymentions() {
		return entitymentions;
	}

	public void setEntitymentions(List<EntitymentionsJson> entitymentions) {
		this.entitymentions = entitymentions;
	}

	public List<TokenJson> getTokens() {
		return tokens;
	}

	public void setTokens(List<TokenJson> tokens) {
		this.tokens = tokens;
	}
	
}
