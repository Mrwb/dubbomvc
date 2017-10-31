<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${controller};

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ${basepackage}.${model}.${className};
import ${basepackage}.${page}.${className}Page;
import ${basepackage}.${service}.${className}Service;
import ${basepackage}.util.Json;

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
@Controller
public class ${className}Controller  {
	
	@Resource
	private ${className}Service ${classNameLower}Service;
	
	
	/**
	 * query
	 * @param ${classNameLower}
	 * @return
	 */
	@RequestMapping("/${className}")
	public ModelAndView query${className}(${className}Page ${classNameLower}Page){
		ModelAndView view=new ModelAndView();
		view.addObject("contentInfoPage", ${classNameLower}Service.find${className}(${classNameLower}Page));
		view.setViewName("/${className}");
		return view;
	}
	
	/**
	 * queryAjax
	 * @param ${classNameLower}
	 * @return
	 */
	@RequestMapping("/${className}/queryAjax")
	@ResponseBody
	public Json queryAjax${className}(${className}Page ${classNameLower}Page){
		Json json = new Json();
		try {
			${className}Page ${classNameLower} = ${classNameLower}Service.find${className}(${classNameLower}Page);
			json.setSuccess(true);
			json.setObject(${classNameLower});
		} catch (Exception e) {
			json.setMessage("Query ${className} fail");
		}
		return json;
	}
	
	/**
	 * queryone${className}
	 * @return
	 */
	@RequestMapping("/${className}/queryone")
	@ResponseBody
	public Json queryone${className}(${className} ${classNameLower}){
		Json json = new Json();
		try {
			${className} tmp${classNameLower} = ${classNameLower}Service.findOne${className}(${classNameLower});
			json.setSuccess(true);
			json.setObject(tmp${classNameLower});
		} catch (Exception e) {
			json.setMessage("Query ${className} fail");
		}
		return json;
	}
	
	/**
	 * save${className}
	 * @return
	 */
	@RequestMapping("/${className}/save")
	@ResponseBody
	public Json save${className}(${className} ${classNameLower}){
		Json json = new Json();
		try {
			int result = ${classNameLower}Service.save${className}(${classNameLower});
			if(result!=0){
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setMessage("Save ${className} fail");
		}
		return json;
	}
	
	/**
	 * update${className}
	 * @return
	 */
	@RequestMapping("/${className}/update")
	@ResponseBody
	public Json update${className}(${className} ${classNameLower}){
		Json json = new Json();
		try {
			int result = ${classNameLower}Service.update${className}(${classNameLower});
			if(result!=0){
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setMessage("Update ${className} fail");
		}
		return json;
	}
	
	/**
	 * delete${className}
	 * @return
	 */
	@RequestMapping("/${className}/del")
	@ResponseBody
	public Json delete${className}(String id){
		Json json = new Json();
		try {
			int result = ${classNameLower}Service.delete${className}(id);
			if(result!=0){
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setMessage("Delete ${className} fail");
		}
		return json;
	}
	
	
}