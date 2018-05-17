<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ${basepackage}.${api}.${module}.${className}Service;
import org.springframework.web.bind.annotation.ResponseBody;
import ${basepackage}.${command}.${className}Command;
import ${basepackage}.${model}.JSON;

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */

@Controller
@RequestMapping("/${className}")
public class UserController extends BaseController{
	@RequestMapping("/query")
	@ResponseBody
	public JSON query(){
		${className}Command command = (${className}Command) beanFactory.getBean("${classNameLower}Command");
		JSON result = command.execute();
		return result;
	}
}