<#include "/macro.include"/>

<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${api};

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
public interface ${className}Service  {
	
	public String query${className}();
	
}