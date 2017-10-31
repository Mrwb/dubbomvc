<#include "/macro.include"/>

<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${baseservice};

import ${basepackage}.${model}.${className};
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.noitom.dao.MybatisDao;
import java.util.List;
import ${basepackage}.${page}.${className}Page;
/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
@Service
public class ${className}BaseService  {
	
	@Resource(name = "dao")
	private MybatisDao dao;

	/**
	 * 获取${className}对象集合
	 * @param ${className}
	 * @return ${className}对象集合
	 */
	public List<${className}> find${className}(${className} ${classNameLower}){
		List<${className}> list = dao.queryForList("${className}Mapper.selectByConditionAll");
		return list;
	}
}