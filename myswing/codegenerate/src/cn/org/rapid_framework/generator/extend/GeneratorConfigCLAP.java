package cn.org.rapid_framework.generator.extend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 2014-11-14
 * @author qiyuchun
 *
 */
public class GeneratorConfigCLAP {
	
	public static final String TEMPLATE = "template";
	
	private static final GeneratorFacade g = new GeneratorFacade();
	
	public static void deleteOutRootDir() throws IOException {
		g.deleteOutRootDir();
	}
	
	/*public static void printAllTableNames() throws Exception {
		g.printAllTableNames();	
	}*/
	
	public static void generateByTable(String tableName) throws Exception {
		g.generateByTable(tableName,TEMPLATE);	
	}
	
	public static void generateByTable(List<TableSingle> tableSingleList) throws Exception {
		//g.generateByTable(tableName,TEMPLATE);	
	}
	
	//qiyuchun 方式一
	public static void generateByTable1(String[][] arr) throws Exception {
		g.generateByTable(convert(arr),TEMPLATE);	
	}
	
	//qiyuchun 方式二
	public static void generateByTable2(String[][] arr) throws Exception {
		g.generateByTable(convert2(arr),TEMPLATE);	
	}
	
	
	//qiyuchun 方式二
	public static void generateByTable(String[][] arr) throws Exception {
		g.generateByTable(convert3(arr),TEMPLATE);	
	}
	
	
	private static Map<String, RelationInfos> convert(String[][] arr) {
		
		Map<String, RelationInfos> map = new HashMap<String, RelationInfos>();
		if(arr!=null && arr.length>0) {
			for (int i = 0; i < arr.length; i++) {
				String[] arri = arr[i];
				if(arri!=null && arri.length > 0) {
					String arri0 = arri[0];
					String arri1 = arri[1];
					String arri2 = arri[2];
					String arri3 = arri[3];
					String arri4 = arri[4];
					RelationInfos relationInfos = map.get(arri4);
					
					
					if(relationInfos!=null) {
						
						if(arri0==null||arri0.equals("") ||
							arri1==null||arri1.equals("") ||
							arri2==null||arri2.equals("") ||
							arri3==null||arri3.equals("") ||
							arri4==null||arri4.equals("")) {
							
							RelationInfo ri = new RelationInfo();
							ri.setPkcol(arri0);
							ri.setPktable(arri1);
							ri.setFkcol(arri2);
							ri.setFktable(arri3);
							ri.setTableName(arri4);
							
							relationInfos.putRelationInfo(ri);
	
							map.put(arri4, relationInfos);
						}
					} else {
						
						if(arri0==null||arri0.equals("") ||
								arri1==null||arri1.equals("") ||
								arri2==null||arri2.equals("") ||
								arri3==null||arri3.equals("") ||
								arri4==null||arri4.equals("")) {
							relationInfos = new RelationInfos();
							RelationInfo ri = new RelationInfo();
							
							ri.setPkcol(arri0);
							ri.setPktable(arri1);
							ri.setFkcol(arri2);
							ri.setFktable(arri3);
							ri.setTableName(arri4);
							
							relationInfos.putRelationInfo(ri);
							
							map.put(arri4, relationInfos);
						}
						
					}
				}
			}
		}
		
		return map;
	}
	
	
	
	private static Map<String, RelationInfos> convert2(String[][] arr) {
		
		Map<String, RelationInfos> map = new HashMap<String, RelationInfos>();
		if(arr!=null && arr.length>0) {
			for (int i = 0; i < arr.length; i++) {
				String[] arri = arr[i];
				if(arri!=null && arri.length > 0) {
					
					String arri0 = arri[0];
					String arri1 = arri[1];
					String arri2 = arri[2];
					String arri3 = arri[3];
					String arri4 = arri[4];
					
					if(arri0!=null&&!arri0.equals("") &&
							arri1!=null&&!arri1.equals("") &&
							arri2!=null&&!arri2.equals("") &&
							arri3!=null&&!arri3.equals("") &&
							arri4!=null&&!arri4.equals("")) {
						
						String tableNames="";
						if(arri4!=null && arri4.equals(RelationShip.ONE)) {
							tableNames = arri1;
						} else if(arri4!=null && arri4.equals(RelationShip.MANY)) {
							tableNames = arri3;
						} else {
							tableNames = arri1 + "&" + arri3;
						}
						
						String tableNameArray[] = tableNames.split("&");
						int length = tableNameArray.length;
						if(tableNameArray!=null && length>0) {
							for (int j = 0; j < length; j++) {
								String tableName = tableNameArray[j];
								
								RelationInfos relationInfos = map.get(tableName);
								
								if(relationInfos!=null) {
										
										RelationInfo ri = new RelationInfo();
										ri.setPkcol(arri0);
										ri.setPktable(arri1);
										ri.setFkcol(arri2);
										ri.setFktable(arri3);
										ri.setType(arri4);
										if(length>1) {
											if(j==0) {
												ri.setType(RelationShip.ONE);
											} else {
												ri.setType(RelationShip.MANY);
											}
										}
										
										relationInfos.putRelationInfo2(ri);
				
										map.put(tableName, relationInfos);
									
								} else {
									
									relationInfos = new RelationInfos();
									RelationInfo ri = new RelationInfo();
									
									ri.setPkcol(arri0);
									ri.setPktable(arri1);
									ri.setFkcol(arri2);
									ri.setFktable(arri3);
									
									ri.setType(arri4);
									if(length>1) {
										if(j==0) {
											ri.setType(RelationShip.ONE);
										} else {
											ri.setType(RelationShip.MANY);
										}
									}
									
									relationInfos.putRelationInfo2(ri);
									
									map.put(tableName, relationInfos);
									
								}

							}
							
						}
						

					} 

				}
			}
		}
		
		return map;
	}
	
	
	
	private static Map<String, RelationInfos> convert3(String[][] arr) {
		
		Map<String, RelationInfos> map = new HashMap<String, RelationInfos>();
		if(arr!=null && arr.length>0) {
			for (int i = 0; i < arr.length; i++) {
				String[] arri = arr[i];
				if(arri!=null && arri.length > 0) {
					
					String arri0 = arri[0];
					String arri1 = arri[1];
					String arri2 = arri[2];
					String arri3 = arri[3];
					
					if(arri0!=null&&!arri0.equals("") &&
							arri1!=null&&!arri1.equals("") &&
							arri2!=null&&!arri2.equals("") &&
							arri3!=null&&!arri3.equals("")) {
						
						String tableNames="";
						tableNames = arri1.trim() + "&" + arri3.trim();
						String tableNameArray[] = tableNames.split("&");
						int length = tableNameArray.length;
						if(tableNameArray!=null && length>0) {
							for (int j = 0; j < length; j++) {
								String tableName = tableNameArray[j];
								
								RelationInfos relationInfos = map.get(tableName);
								
								if(relationInfos!=null) {
										
										RelationInfo ri = new RelationInfo();
										ri.setPkcol(arri0);
										ri.setPktable(arri1);
										ri.setFkcol(arri2);
										ri.setFktable(arri3);
										
										if(j==0) {
											ri.setType(RelationShip.ONE);
										} else {
											ri.setType(RelationShip.MANY);
										}
										
										relationInfos.putRelationInfo3(ri);
				
										map.put(tableName, relationInfos);
									
								} else {
									
									relationInfos = new RelationInfos();
									RelationInfo ri = new RelationInfo();
									
									ri.setPkcol(arri0);
									ri.setPktable(arri1);
									ri.setFkcol(arri2);
									ri.setFktable(arri3);
									
									if(j==0) {
										ri.setType(RelationShip.ONE);
									} else {
										ri.setType(RelationShip.MANY);
									}
									
									relationInfos.putRelationInfo3(ri);
									
									map.put(tableName, relationInfos);
									
								}

							}
							
						}
						

					} 

				}
			}
		}
		
		return map;
	}
	
	

	
	
	
	
	public static void main(String[] args) throws Exception {
		GeneratorFacade g = new GeneratorFacade();
//		g.printAllTableNames();				//打印数据库中的表名称
		
		g.deleteOutRootDir();							//删除生成器的输出目录
		//g.generateByAllTable("template");	//自动搜索数据库中的所有表并生成文件,template为模板的根目录
		
		g.generateByTable("tt_user","template");	
		g.generateByTable("tt_role","template");
		g.generateByTable("tt_user_role","template");
		//g.generateByTable("t_demo","template");
		//g.generateByTable("tt_demm","template");
		
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
	}

	
}
