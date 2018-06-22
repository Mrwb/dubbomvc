<#include "/macro.include"/>

<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${provider};

import org.springframework.beans.factory.annotation.Autowired;

import ${basepackage}.${model}.${module}.${className};
import ${basepackage}.${api}.${module}.${className}Service;
import ${basepackage}.${service}.${className}BaseService;
import ${basepackage}.${model}.JsonResponse;
/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
public class ${className}ServiceImpl implements ${className}Service  {

	@Autowired
	private ${className}BaseService ${classNameLower}BaseService;
	
	public JsonResponse query(${className} ${classNameLower}) {
		JsonResponse json = new JsonResponse();
		${className} ${classNameLower}tmp = ${classNameLower}BaseService.findOne${className}(${classNameLower});
		json.setData(${classNameLower}tmp);
	    return json;
	}
	
	public JsonResponse save(${className} ${classNameLower}) {
		JsonResponse json = new JsonResponse();
		int result = ${classNameLower}BaseService.save${className}(${classNameLower});
		json.setData(result);
	    return json;
	}
	
	public JsonResponse edit(${className} ${classNameLower}) {
		JsonResponse json = new JsonResponse();
		int result = ${classNameLower}BaseService.update${className}(${classNameLower});
		json.setData(result);
	    return json;
	}
	
	public JsonResponse delete(${className} ${classNameLower}) {
		JsonResponse json = new JsonResponse();
		int result = ${classNameLower}BaseService.delete${className}(${classNameLower}.getId());
		json.setData(result);
	    return json;
	}
	
	
}