import cn.org.rapid_framework.generator.GeneratorCLAP;
import cn.org.rapid_framework.generator.GeneratorProperties;





public class GeneratorMain {
	
	public static void main(String[] args) throws Exception {
		
		//删除输出目录
		GeneratorCLAP.deleteOutRootDir();
		

		//根据表名称生成文件
//		GeneratorCLAP.generateByTable("u_area");
//		GeneratorCLAP.generateByTable("u_user");
//		GeneratorCLAP.generateByTable("u_organization_info");
//		GeneratorCLAP.generateByTable("u_organization");
//		GeneratorCLAP.generateByTable("u_permission");
//		GeneratorCLAP.generateByTable("u_role_permission");
//		GeneratorCLAP.generateByTable("u_role");
//		GeneratorCLAP.generateByTable("u_user_organization");
//		GeneratorCLAP.generateByTable("u_user_property");
//		GeneratorCLAP.generateByTable("u_user_role");
//		GeneratorCLAP.generateByTable("u_log");
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
//		GeneratorCLAP.generateByTable("invoice_yewifi_merchant"); 
//		GeneratorCLAP.generateByTable("cert_invoice"); 
//		GeneratorCLAP.generateByTable("cert_taxpayer_property"); 
//		GeneratorCLAP.generateByTable("invoice_detail"); 
//		GeneratorCLAP.generateByTable("invoice_init"); 
//		GeneratorCLAP.generateByTable("invoice_item"); 
		GeneratorCLAP.generateByTable("invoice_express_info"); 
//		GeneratorCLAP.generateByTable("invoice_merchant"); 
//		GeneratorCLAP.generateByTable("check_invoice_vat"); 
//		GeneratorCLAP.generateByTable("check_invoice_freightage"); 
//		GeneratorCLAP.generateByTable("check_invoice_motorvehicle"); 
//		GeneratorCLAP.generateByTable("check_invoice_secondhandcar"); 
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
		
	}
}
