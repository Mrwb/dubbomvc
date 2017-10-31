package cn.org.rapid_framework.generator.extend;

public class RelationInfo {

	 //关联关系的主表
	 private String pktable;
	 //关联关系的主键列
	 private String pkcol;
	 //关联关系的外键表
	 private String fktable;
	 //关联关系的外键列
	 private String fkcol;

	 private String seq;
	 
	 private String tableName;
	 
	 //一端，多端，双向
	 private String type;
	 
	 
	 public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPktable() {
		return pktable;
	}
	public void setPktable(String pktable) {
		this.pktable = pktable;
	}
	public String getPkcol() {
		return pkcol;
	}
	public void setPkcol(String pkcol) {
		this.pkcol = pkcol;
	}
	public String getFktable() {
		return fktable;
	}
	public void setFktable(String fktable) {
		this.fktable = fktable;
	}
	public String getFkcol() {
		return fkcol;
	}
	public void setFkcol(String fkcol) {
		this.fkcol = fkcol;
	}

	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
}
