<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${restservice};

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

import ${basepackage}.${model}.${className};
import ${basepackage}.model.JSON;
import ${basepackage}.${interf}.${className}Service;

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
@Path("/${classNameLower}")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ${className}RestService implements ${className}Service {
	
	private ${className}Service ${classNameLower}Service;
	
    public void set${className}Service(${className}Service ${classNameLower}Service) {
        this.${classNameLower}Service = ${classNameLower}Service;
    }
	
	/**
	 * query
	 * @return JSON
	 */
	@POST
	@Path("/query${className}")
	public JSON query${className}(){
		return ${classNameLower}Service.query${className}();
	}
	
}