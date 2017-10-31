package cn.org.rapid_framework.generator.extend;

import java.util.ArrayList;
import java.util.List;






public class GeneratorMain {
	
	public static void main(String[] args) throws Exception {
		
		//删除输出目录
		//GeneratorConfigCLAP.deleteOutRootDir();
		
		
		//主表    
		//GeneratorCLAP.generateByTable("ttt_user");
		//GeneratorCLAP.generateByTable("ttt_role");
		
		//从表
		//GeneratorCLAP.generateByTable("ttt_user_role");
		//GeneratorCLAP.generateByTable("t_demo");
		

		
		/*
		String arr[][] = {
				{"id","ttt_user","ttt_user_id","ttt_user_role","ttt_user"},
				{"id","ttt_user","ttt_user_id","ttt_user_role","ttt_user_role"},
				{"id","ttt_role","ttt_role_id","ttt_user_role","ttt_role"},
				{"id","ttt_role","ttt_role_id","ttt_user_role","ttt_user_role"},
				{"id","ttt_user_role","ttt_u_r_id","ttt_company","ttt_user_role"},
				{"id","ttt_user_role","ttt_u_r_id","ttt_company","ttt_company"}
		};
		*/ 
		
		/*
		String arr[][] = {
				{"id","ttt_user","ttt_user_id","ttt_user_role","ttt_user_role"},
				{"id","ttt_role","ttt_role_id","ttt_user_role","ttt_role"},
				{"id","ttt_user_role","ttt_u_r_id","ttt_company","ttt_company"}
		}; 
		
		GeneratorConfigCLAP.generateByTable(arr);
		*/
		
		
		
		/*
		String arr[][] = {
				{"id","ttt_user","ttt_user_id","ttt_user_role",RelationShip.ONE},
				{"id","ttt_role","ttt_role_id","ttt_user_role",RelationShip.TWO},
				{"id","ttt_user_role","ttt_u_r_id","ttt_company",RelationShip.MANY}
		}; 

		String arr[][] = {
				{"id","ttt_user","ttt_user_id","ttt_user_role",RelationShip.ONE},
		}; 
		GeneratorConfigCLAP.generateByTable2(arr);
		*/
		
		/*String arr[][] = {
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_role","ttt_role_id","ttt_user_role"},
				{"id","ttt_user_role","ttt_u_r_id","ttt_company"}
		}; 
		 */
		
		
		String arr[][] = {
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_role","ttt_role_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"}
		}; 
		
		GeneratorConfigCLAP.deleteOutRootDir();
		GeneratorConfigCLAP.generateByTable(arr);
		
		
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
		
	}
}
