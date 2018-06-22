import cn.org.rapid_framework.generator.GeneratorCLAP;
import cn.org.rapid_framework.generator.GeneratorProperties;





public class GeneratorMain {
	
	public static void main(String[] args) throws Exception {
		
		//删除输出目录
		GeneratorCLAP.deleteOutRootDir();
		

		//根据表名称生成文件
/*		GeneratorCLAP.generateByTable("area");
		GeneratorCLAP.generateByTable("user");
		GeneratorCLAP.generateByTable("org_company_info");
		GeneratorCLAP.generateByTable("organization");
		GeneratorCLAP.generateByTable("permission");
		GeneratorCLAP.generateByTable("role_permission");
		GeneratorCLAP.generateByTable("role");
		GeneratorCLAP.generateByTable("user_organization");
		GeneratorCLAP.generateByTable("user_property");
		GeneratorCLAP.generateByTable("user_role");*/
//		GeneratorCLAP.generateByTable("general_interface_statistics");
//		GeneratorCLAP.generateByTable("general_interface_statistics_month");
//		GeneratorCLAP.generateByTable("app_company");
//		GeneratorCLAP.generateByTable("app_login_info");
//		GeneratorCLAP.generateByTable("app_user");
//		GeneratorCLAP.generateByTable("app_user_company");
//		GeneratorCLAP.generateByTable("app_message");
//		GeneratorCLAP.generateByTable("app_invoice"); 
//		GeneratorCLAP.generateByTable("invoice_yewifi_notify"); 
//		GeneratorCLAP.generateByTable("invoice_yewifi_info"); 
//		GeneratorCLAP.generateByTable("invoice_yewifi_merchant_notify"); 
		GeneratorCLAP.generateByTable("invoice_yewifi_merchant"); 
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
		
	}
}
