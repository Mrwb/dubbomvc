<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${service};

import javax.annotation.Resource;
import ${basepackage}.${model}.${className};
import ${basepackage}.${baseservice}.${className}BaseService;
import ${basepackage}.model.JSON;
import ${basepackage}.${interf}.${className}Service;

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
public class ${className}ServiceImpl implements ${className}Service {
    
	@Resource
	private ${className}BaseService ${classNameLower}BaseService;
    
	/**
	 * query${className}
	 * @return JSON
	 */
	public JSON query${className}(){
		JSON json = new JSON();
		// TO DO
		return json;
	}
	
}