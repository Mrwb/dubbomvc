<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${model};

import java.util.Date;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
<#if table.remarks??>//${table.remarks}</#if>
public class ${className} implements Serializable {

	<#list table.columns as column>
		<#if column.columnNameFirstLower??>
			<#if !idAnddefaultColum?contains(column.sqlName)>
	<#if column.remarks??>//${column.remarks}</#if>
	private ${column.simpleJavaType} ${column.columnNameFirstLower};
			</#if>
		</#if>
	</#list>
	

<@generateConstructor className/>
<@generateJavaColumns/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>


<#-- 

	@Override
	public String toString() {
		return  "${className} ["
		<#list table.columns as column>  
		<#if column.columnNameFirstLower??>
			<#if !idAnddefaultColum?contains(column.sqlName)>
			+ "${column.columnName} = " + get${column.columnName}() + ","
			<#if column_has_next>+","</#if>
			</#if>
		</#if>
		</#list>
		<#list table.exportedKeys.associatedTables?values as foreignKey>
			<#assign fkSqlTable = foreignKey.sqlTable>
			<#assign fkTable    = fkSqlTable.className>
			<#assign fkPojoClass = fkSqlTable.className>
			<#assign fkPojoClassVar = fkPojoClass?uncap_first>
			+ "${fkPojoClassVar}s = " + get${fkPojoClass}s() + ","
		</#list>
		<#list table.importedKeys.associatedTables?values as foreignKey>
			<#assign fkSqlTable = foreignKey.sqlTable>
			<#assign fkTable    = fkSqlTable.className>
			<#assign fkPojoClass = fkSqlTable.className>
			<#assign fkPojoClassVar = fkPojoClass?uncap_first>
			+ "${fkPojoClassVar} = " + get${fkPojoClass}()<#if foreignKey_has_next>+","</#if>
		</#list>
		+"]";
	}
	
}
-->

<#macro generateJavaColumns>
	<#list table.columns as column>

		<#if column.columnNameFirstLower??>
			<#if !idAnddefaultColum?contains(column.sqlName)>
	public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameFirstLower}) {
		this.${column.columnNameFirstLower} = ${column.columnNameFirstLower};
	}
	
	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameFirstLower};
	}
			</#if>
		</#if>
	</#list>
</#macro>

}

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private List<${fkPojoClass}> ${fkPojoClassVar}s = new ArrayList<${fkPojoClass}>();
	public void set${fkPojoClass}s(List<${fkPojoClass}> ${fkPojoClassVar}s){
		this.${fkPojoClassVar}s = ${fkPojoClassVar}s;
	}
	
	public List<${fkPojoClass}> get${fkPojoClass}s() {
		return this.${fkPojoClassVar}s;
	}
	</#list>
</#macro>


<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>




<#-- 

<#macro generateJavaOneToMany>
<#list table.exportedKeys.associatedTables?values as foreignKey>
<#assign fkSqlTable = foreignKey.sqlTable>
<#assign fkTable    = fkSqlTable.className>
<#assign fkPojoClass = fkSqlTable.className>
<#assign fkPojoClassVar = fkPojoClass?uncap_first>

private Set ${fkPojoClassVar}s = new HashSet(0);
public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
	this.${fkPojoClassVar}s = ${fkPojoClassVar};
}

public Set<${fkPojoClass}> get${fkPojoClass}s() {
	return ${fkPojoClassVar}s;
}
</#list>
</#macro>

-->
