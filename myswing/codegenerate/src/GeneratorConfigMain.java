import cn.org.rapid_framework.generator.GeneratorCLAP;
import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.extend.GeneratorConfigCLAP;





public class GeneratorConfigMain {
	
	public static void main(String[] args) throws Exception {
		

		String arr[][] = {
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_role","ttt_role_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_user","ttt_user_id","ttt_user_role"},
				{"id","ttt_user_role","ttt_u_r_id","ttt_company"}
		}; 
		
		
		//删除输出目录
		GeneratorCLAP.deleteOutRootDir();
		
		GeneratorConfigCLAP.generateByTable(arr);
		
		
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
		
	}
}
