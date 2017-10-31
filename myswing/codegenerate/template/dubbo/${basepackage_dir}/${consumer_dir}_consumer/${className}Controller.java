<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ${basepackage}.${api}.${className}Service;

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */

@Controller
public class ${className}Controller{
	@Autowired
	${className}Service ${classNameLower}Service;
	@RequestMapping("/${className}")
	public String ${className}(Model model){
        String hello = ${classNameLower}Service.query${className}(); // 执行远程方法
        System.out.println(hello); // 显示调用结果
		return "/${className}";
	}
}