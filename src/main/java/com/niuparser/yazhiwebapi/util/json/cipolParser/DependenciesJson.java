package com.niuparser.yazhiwebapi.util.json.cipolParser;

public class DependenciesJson {

	private String dep;
	
	private Integer governor;
	
	private String governorGloss;
	
	private Integer dependent;
	
	private String dependentGloss;

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public Integer getGovernor() {
		return governor;
	}

	public void setGovernor(Integer governor) {
		this.governor = governor;
	}

	public String getGovernorGloss() {
		return governorGloss;
	}

	public void setGovernorGloss(String governorGloss) {
		this.governorGloss = governorGloss;
	}

	public Integer getDependent() {
		return dependent;
	}

	public void setDependent(Integer dependent) {
		this.dependent = dependent;
	}

	public String getDependentGloss() {
		return dependentGloss;
	}

	public void setDependentGloss(String dependentGloss) {
		this.dependentGloss = dependentGloss;
	}
	
}
