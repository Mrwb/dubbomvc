package cn.org.rapid_framework.generator.extend;

public class TableSingle {

	 //关联关系的主表
	 private String pktable;
	 //关联关系的主键列
	 private String pkcol;
	 //关联关系的外键表
	 private String fktable;
	 //关联关系的外键列
	 private String fkcol;
	 //中心表，真正生成文件的表
	 private String tableName;
	 private String seq;
	 
	 
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
