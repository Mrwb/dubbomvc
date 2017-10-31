<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${page};

import java.io.Serializable;
import java.util.List;
import ${basepackage}.${model}.${className};

/**
 * @version 1.0
 * @author Eric.wang
 * @introduction 分页page
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
<#if table.remarks??>//${table.remarks}</#if>
public class ${className}Page extends ${className} implements Serializable{
	/**
	 * 分页显示条数
	 */
	private int rows = 10; 
	/**
	 * 分页总页数
	 */
	private int pageNum;
	/**
	 * 当前页数
	 */
	private int currentPage; 
	/**
	 * 总条数
	 */
	private int totalNum;
	
	/**
	 * 开始分页基数
	 */
	private int startPage;
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * 排序方式
	 */
	private String order;
	/**
	 * 查询对象集合
	 */
	private List<?> list;
	
	/**
	 * 获取分页显示条数 
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * 设置分页显示条数
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * 获取页数
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * 设置页数
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 获取总条数
	 */
	public int getTotalNum() {
		return totalNum;
	}
	/**
	 * 设置总条数
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * 获取排序字段
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * 设置排序字段
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * 获取排序方式
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * 设置排序方式
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * 获取分页对象信息集合
	 */
	public List<?> getList() {
		return list;
	}
	/**
	 * 设置分页对象信息集合
	 */
	public void setList(List<?> list) {
		this.list = list;
	}
	/**
	 * 获取分页开始条数 limit  {start}
	 */
	public int getStartPage() {
		if(currentPage == 0 || currentPage == 1){
			this.startPage = 0;
		}else{
			this.startPage = (currentPage -1) * rows;
		}
		return startPage;
	}
	public int getPageNum() {
		return (totalNum-1)/rows+1;
	}
}