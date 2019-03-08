package com.niuparser.yazhiwebapi.util.json.cipolParser;

public class OpenieJson {

	private String subject;
	
	private Integer[] subjectSpan;
	
	private String relation;
	
	private Integer[] relationSpan;
	
	private String object;
	
	private Integer[] objectSpan;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer[] getSubjectSpan() {
		return subjectSpan;
	}

	public void setSubjectSpan(Integer[] subjectSpan) {
		this.subjectSpan = subjectSpan;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Integer[] getRelationSpan() {
		return relationSpan;
	}

	public void setRelationSpan(Integer[] relationSpan) {
		this.relationSpan = relationSpan;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Integer[] getObjectSpan() {
		return objectSpan;
	}

	public void setObjectSpan(Integer[] objectSpan) {
		this.objectSpan = objectSpan;
	}
	
}
