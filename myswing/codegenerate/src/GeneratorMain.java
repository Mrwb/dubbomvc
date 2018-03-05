import cn.org.rapid_framework.generator.GeneratorCLAP;
import cn.org.rapid_framework.generator.GeneratorProperties;





public class GeneratorMain {
	
	public static void main(String[] args) throws Exception {
		
		//删除输出目录
		GeneratorCLAP.deleteOutRootDir();
		

		//根据表名称生成文件
//		GeneratorCLAP.generateByTable("approval_flow_history");
		GeneratorCLAP.generateByTable("user");
//		GeneratorCLAP.generateByTable("approval_flow");
//		GeneratorCLAP.generateByTable("approval_info");
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
		
	}
}
