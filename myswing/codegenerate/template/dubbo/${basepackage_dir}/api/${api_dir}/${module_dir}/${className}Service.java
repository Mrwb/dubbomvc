<#include "/macro.include"/>

<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${api}.${module};

import ${basepackage}.${model}.JsonResponse;
import ${basepackage}.${model}.${module}.${className};
/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
public interface ${className}Service  {
	
	public JsonResponse query(${className} ${classNameLower});

	public JsonResponse save(${className} ${classNameLower});
	
	public JsonResponse edit(${className} ${classNameLower});
	
	public JsonResponse delete(${className} ${classNameLower});
	
}