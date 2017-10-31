package cn.org.rapid_framework.generator.extend;

import java.util.ArrayList;
import java.util.List;

public class RelationInfos {

	private List<RelationInfo> importRelationInfos = new ArrayList<RelationInfo>();
	private List<RelationInfo> exportRelationInfos = new ArrayList<RelationInfo>();
	
	
	
	public void putImportRelationInfos(RelationInfo relationInfo) {
		if(relationInfo!=null) {
			importRelationInfos.add(relationInfo);
		}
	}
	
	public void putExportRelationInfos(RelationInfo relationInfo) {
		if(relationInfo!=null) {
			exportRelationInfos.add(relationInfo);
		}
	}
	
	public void putRelationInfo(RelationInfo relationInfo) {
		if(relationInfo!=null) {
			String fkTable = relationInfo.getFktable();
			String pkTable = relationInfo.getPktable();
			String tableName = relationInfo.getTableName();
			if(fkTable!=null && !fkTable.equals("")
					&&pkTable!=null && !pkTable.equals("")
					&&tableName!=null && !tableName.equals("") && pkTable.equals(tableName)) {
				exportRelationInfos.add(relationInfo);
			} else {
				importRelationInfos.add(relationInfo);
			}
		}
	}
	
	
	
	public void putRelationInfo2(RelationInfo relationInfo) {
		if(relationInfo!=null) {
			String fkTable = relationInfo.getFktable();
			String pkTable = relationInfo.getPktable();
			String type = relationInfo.getType();
			if(type!=null && type.equals(RelationShip.ONE)) {
				exportRelationInfos.add(relationInfo);
			} else if(type!=null && type.equals(RelationShip.MANY)){
				importRelationInfos.add(relationInfo);
			} 
		}
	}
	
	
	public void putRelationInfo3(RelationInfo relationInfo) {
		if(relationInfo!=null) {
			String fkTable = relationInfo.getFktable();
			String pkTable = relationInfo.getPktable();
			String type = relationInfo.getType();
			if(type!=null && type.equals(RelationShip.ONE)) {
				exportRelationInfos.add(relationInfo);
			} else if(type!=null && type.equals(RelationShip.MANY)){
				importRelationInfos.add(relationInfo);
			} 
		}
	}
	
	
	public List<RelationInfo> getImportRelationInfos() {
		return importRelationInfos;
	}
	public void setImportRelationInfos(List<RelationInfo> importRelationInfos) {
		this.importRelationInfos = importRelationInfos;
	}
	
	
	public List<RelationInfo> getExportRelationInfos() {
		return exportRelationInfos;
	}
	public void setExportRelationInfos(List<RelationInfo> exportRelationInfos) {
		this.exportRelationInfos = exportRelationInfos;
	}
	
}
