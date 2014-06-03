package com.service;

public class Summary {
	
	public String statement;
	public String severity;
	public String keyword;
	public boolean endPara;
	public Summary(String statement, String severity, String keyword,boolean endPara){
		this.statement = statement;
		this.severity = severity;
		this.keyword = keyword;
		this.endPara = endPara;
	}
	
	public String toString(){
		return "DataObject [statement=" + statement + ", severity=" + severity + ", keyword="
				+ keyword +", endPara="+endPara +"]";
	}

}
