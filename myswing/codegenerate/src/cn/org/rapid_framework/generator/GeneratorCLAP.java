package cn.org.rapid_framework.generator;

import java.io.IOException;


/**
 * 2014-11-06
 * @author qiyuchun
 *
 */
public class GeneratorCLAP {
	
	public static final String TEMPLATE = "template";
	
	private static final GeneratorFacade g = new GeneratorFacade();
	
	public static void deleteOutRootDir() throws IOException {
		g.deleteOutRootDir();
	}
	
	public static void printAllTableNames() throws Exception {
		g.printAllTableNames();	
	}
	
	public static void generateByTable(String tableName) throws Exception {
		g.generateByTable(tableName,TEMPLATE);	
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
