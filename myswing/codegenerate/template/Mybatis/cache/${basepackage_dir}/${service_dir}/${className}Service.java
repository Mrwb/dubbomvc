<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${service};
import ${basepackage}.${model}.${className};
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import ${basepackage}.dao.MybatisDao;
import java.util.List;
import ${basepackage}.${page}.${className}Page;

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
@Service
public class ${className}Service  {
	
	@Resource(name = "dao")
	private MybatisDao dao;
	/**
	 * 保存${className}对象
	 * @param ${className}
	 * @return 影响条数
	 */
	public int save${className}(${className} ${classNameLower}){
		return dao.insertObj("${className}Mapper.insert", ${classNameLower});
	}
	
	/**
	 * 删除${className}对象
	 * @param ${className}的ID
	 * @return 影响条数
	 */
	public int delete${className}(String id){
		return dao.deleteObjById("${className}Mapper.deleteById", id);
	}
	
	/**
	 * 修改${className}对象
	 * @param ${className}
	 * @return 影响条数
	 */
	public int update${className}(${className} ${classNameLower}){
		return dao.updateObj("${className}Mapper.updateById", ${classNameLower});
	}
	
	/**
	 * 获取${className}对象
	 * @param ${className}的ID
	 * @return ${className}对象
	 */
	public ${className} get${className}(String id){
		return (${className})dao.queryForId("${className}Mapper.selectById", id);
	}
	
	/**
	 * 获取${className}对象集合
	 * @param Page
	 * @return ${className}对象集合
	 */
	public ${className}Page find${className}(${className}Page ${classNameLower}Page){
		int pagecount = (int) dao.queryForCount("${className}Mapper.findCount",${classNameLower}Page);
		${classNameLower}Page.setTotalNum(pagecount);
		List<${className}> list = (List<${className}>)dao.queryForList("${className}Mapper.selectByCondition", ${classNameLower}Page);
		${classNameLower}Page.setList(list);
		return ${classNameLower}Page;
	}
	
	/**
	 * 获取${className}对象集合
	 * @param ${className}
	 * @return ${className}对象集合
	 */
	public List<${className}> find${className}(${className} ${classNameLower}){
		List<${className}> list = (List<${className}>)dao.queryForList("${className}Mapper.selectByConditionAll", ${classNameLower});
		return list;
	}
	
	/**
	 * 获取${className}对象
	 * @param ${className}
	 * @return ${className}对象
	 */
	public ${className} findOne${className}(${className} ${classNameLower}){
		${className} tmp${classNameLower} = (${className}) dao.queryForObject("${className}Mapper.selectOneByCondition", ${classNameLower});
		return tmp${classNameLower};
	}
}