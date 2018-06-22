<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ${basepackage}.${api}.${module}.${className}Service;
import org.springframework.web.bind.annotation.ResponseBody;
import ${basepackage}.${model}.JsonResponse;
import ${basepackage}.${model}.${module}.${className};
/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */

@Controller
@RequestMapping("/${className}")
public class ${className}Controller{
	
	@Autowired
	private ${className}Service ${classNameLower}Service;
	
	@RequestMapping("/query")
	@ResponseBody
	public JsonResponse query(@RequestBody ${className} ${classNameLower}){
		JsonResponse result = ${classNameLower}Service.query(${classNameLower});
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public JsonResponse save(@RequestBody ${className} ${classNameLower}){
		JsonResponse result = ${classNameLower}Service.save(${classNameLower});
		return result;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public JsonResponse edit(@RequestBody ${className} ${classNameLower}){
		JsonResponse result = ${classNameLower}Service.edit(${classNameLower});
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResponse delete(@RequestBody ${className} ${classNameLower}){
		JsonResponse result = ${classNameLower}Service.delete(${classNameLower});
		return result;
	}
}