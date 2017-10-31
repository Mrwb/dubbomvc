<#include "/macro.include"/>

<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${provider};

import org.springframework.beans.factory.annotation.Autowired;
import ${basepackage}.${model}.${className};
import ${basepackage}.${api}.${className}Service;
import ${basepackage}.${service}.${className}BaseService;
/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
public class ${className}ServiceImpl implements ${className}Service  {

	@Autowired
	private ${className}BaseService ${classNameLower}BaseService;
	
	public String query${className}() {
		${className} ${classNameLower}tmp = new ${className}();
		${className} ${classNameLower} = ${classNameLower}BaseService.findOne${className}(${classNameLower}tmp);
	    return "answer is "+${classNameLower}.toString();
	}
}