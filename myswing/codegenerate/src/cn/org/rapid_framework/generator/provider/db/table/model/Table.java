package cn.org.rapid_framework.generator.provider.db.table.model;


import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.extend.RelationInfo;
import cn.org.rapid_framework.generator.extend.RelationInfos;
import cn.org.rapid_framework.generator.provider.db.table.TableFactory;
import cn.org.rapid_framework.generator.util.StringHelper;
/**
 * 

下为代码生成器的模板可以引用的table变量的参考. 
Table.java 参考,引用变量名为:table
Table对象为对应的数据库表 

	属性  描述  示例值  
	sqlName  数据库的列名称,很多其它值都是通过此列派生  USER_INFO 
	className  通过sqlName生成的类名称  UserInfo 
	classNameFirstLower  类名称第一个字母小写  userInfo 
	classNameLowerCase  className并全部小写  userinfo 
	underscoreName  下划线类名称  user_info 
	columns  数据库全部column  请查阅Column.java 
	remarks  数据库的注释  null 
	notPkColumns  非主键的column对象   
	pkCount  主键数  1 
	singleId  pkCount = 1时为true  true 
	compositeId  pkCount >= 2时为true  false 
	idColumn  表的主键  USER_ID(已经废弃) 
	pkColumns  所有的主键列,column对象   
	compositeIdColumns  等价于pkColumns,该属性已经过时  
	tableAlias  表的别名,值为 remarks == null ? className : remarks  UserInfo 
	notCompositeId  pkCount < 2 时为true true 
	primaryKeyColumns  所有主键列   
	ownerSynonymName    
	constantName  常量名,等于sqlName.toUpperCase()  USER_INFO 
	importedKeys    
	exportedKeys  

 * 
 * 
 * Table对象为对应的数据库表 * 
 * 根据表结构建立的对象。
 * 用于生成代码的Table对象.对应数据库的table
 */
public class Table {

	String sqlName;  //数据库的列名称,很多其它值都是通过此列派生  USER_INFO
	String remarks;
	String className;  //通过sqlName生成的类名称   UserInfo
	String classNameLowerCase;
	/** the name of the owner of the synonym if this table is a synonym */
	private String ownerSynonymName = null;
	LinkedHashSet<Column> columns = new LinkedHashSet<Column>();
	List<Column> primaryKeyColumns = new ArrayList<Column>();
	
	public Table() {}
	
	public Table(Table t) {
		setSqlName(t.getSqlName());
		this.remarks = t.getRemarks();
		this.className = t.getSqlName();
		this.classNameLowerCase = t.getSqlName().toLowerCase();
		this.ownerSynonymName = t.getOwnerSynonymName();
		this.columns = t.getColumns();
		this.primaryKeyColumns = t.getPrimaryKeyColumns();
		this.tableAlias = t.getTableAlias();
		this.exportedKeys = t.exportedKeys;
		this.importedKeys = t.importedKeys;
	}
	
	public LinkedHashSet<Column> getColumns() {
		return columns;
	}
	public void setColumns(LinkedHashSet<Column> columns) {
		this.columns = columns;
	}
	public String getOwnerSynonymName() {
		return ownerSynonymName;
	}
	public void setOwnerSynonymName(String ownerSynonymName) {
		this.ownerSynonymName = ownerSynonymName;
	}
	
	/** 使用 getPkColumns() 替换*/
	@Deprecated
	public List<Column> getPrimaryKeyColumns() {
		return primaryKeyColumns;
	}
	/** 使用 setPkColumns() 替换*/
	@Deprecated
	public void setPrimaryKeyColumns(List<Column> primaryKeyColumns) {
		this.primaryKeyColumns = primaryKeyColumns;
	}
	/** 数据库中表的表名称,其它属性很多都是根据此属性派生 */
	public String getSqlName() {
		return sqlName;
	}
	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public static String removeTableSqlNamePrefix(String sqlName) {
		String prefixs = GeneratorProperties.getProperty("tableRemovePrefixes", "");
		for(String prefix : prefixs.split(",")) {
			String removedPrefixSqlName = StringHelper.removePrefix(sqlName, prefix,true);
			if(!removedPrefixSqlName.equals(sqlName)) {
				return removedPrefixSqlName;
			}
		}
		return sqlName;
	}
	
	/** 数据库中表的表备注 */
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void addColumn(Column column) {
		columns.add(column);
	}
	
	public void setClassName(String customClassName) {
		this.className = customClassName;
	}
	/**
	 * 根据sqlName得到的类名称，示例值: UserInfo
	 * @return
	 */
	public String getClassName() {
	    if(StringHelper.isBlank(className)) {
	        String removedPrefixSqlName = removeTableSqlNamePrefix(sqlName);
	        className = StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(removedPrefixSqlName));
	    }
		return className;
	}
	/** 数据库中表的别名，等价于:  getRemarks().isEmpty() ? getClassName() : getRemarks() */
	public String getTableAlias() {
		if(StringHelper.isNotBlank(tableAlias)) return tableAlias;
		return StringHelper.removeCrlf(StringHelper.defaultIfEmpty(getRemarks(), getClassName()));
	}
	public void setTableAlias(String v) {
		this.tableAlias = v;
	}
	
	/**
	 * 等价于getClassName().toLowerCase()
	 * @return
	 */
	public String getClassNameLowerCase() {
		return getClassName().toLowerCase();
	}
	/**
	 * 得到用下划线分隔的类名称，如className=UserInfo,则underscoreName=user_info
	 * @return
	 */
	public String getUnderscoreName() {
		return StringHelper.toUnderscoreName(getClassName()).toLowerCase();
	}
	/**
	 * 返回值为getClassName()的第一个字母小写,如className=UserInfo,则ClassNameFirstLower=userInfo
	 * @return
	 */
	public String getClassNameFirstLower() {
		return StringHelper.uncapitalize(getClassName());
	}
	
	/**
	 * 根据getClassName()计算而来,用于得到常量名,如className=UserInfo,则constantName=USER_INFO
	 * @return
	 */
	public String getConstantName() {
		return StringHelper.toUnderscoreName(getClassName()).toUpperCase();
	}
	
	/** 使用 getPkCount() 替换*/
	@Deprecated
	public boolean isSingleId() {
		return getPkCount() == 1 ? true : false;
	}
	
	/** 使用 getPkCount() 替换*/
	@Deprecated
	public boolean isCompositeId() {
		return getPkCount() > 1 ? true : false;
	}

	/** 使用 getPkCount() 替换*/
	@Deprecated
	public boolean isNotCompositeId() {
		return !isCompositeId();
	}
	
	/**
	 * 得到主键总数
	 * @return
	 */
	public int getPkCount() {
		int pkCount = 0;
		for(Column c : columns){
			if(c.isPk()) {
				pkCount ++;
			}
		}
		return pkCount;
	}
	/**
	 * use getPkColumns()
	 * @deprecated 
	 */
	public List getCompositeIdColumns() {
		return getPkColumns();
	}
	
	/**
	 * 得到是主键的全部column
	 * @return
	 */	
	public List<Column> getPkColumns() {
		List results = new ArrayList();
		for(Column c : getColumns()) {
			if(c.isPk())
				results.add(c);
		}
		return results;
	}
	
	/**
	 * 得到不是主键的全部column
	 * @return
	 */
	public List<Column> getNotPkColumns() {
		List results = new ArrayList();
		for(Column c : getColumns()) {
			if(!c.isPk())
				results.add(c);
		}
		return results;
	}
	/** 得到单主键，等价于getPkColumns().get(0)  */
	public Column getPkColumn() {
		if(getPkColumns().isEmpty()) {
			throw new IllegalStateException("not found primary key on table:"+getSqlName());
		}
		return getPkColumns().get(0);
	}
	
	/**使用 getPkColumn()替换 */
	@Deprecated
	public Column getIdColumn() {
		return getPkColumn();
	}
	
	public List<Column> getEnumColumns() {
        List results = new ArrayList();
        for(Column c : getColumns()) {
            if(!c.isEnumColumn())
                results.add(c);
        }
        return results;	    
	}
	
	public Column getColumnByName(String name) {
	    Column c = getColumnBySqlName(name);
	    if(c == null) {
	    	c = getColumnBySqlName(StringHelper.toUnderscoreName(name));
	    }
	    return c;
	}
	
	public Column getColumnBySqlName(String sqlName) {
	    for(Column c : getColumns()) {
	        if(c.getSqlName().equalsIgnoreCase(sqlName)) {
	            return c;
	        }
	    }
	    return null;
	}
	
   public Column getRequiredColumnBySqlName(String sqlName) {
       if(getColumnBySqlName(sqlName) == null) {
           throw new IllegalArgumentException("not found column with sqlName:"+sqlName+" on table:"+getSqlName());
       }
       return getColumnBySqlName(sqlName);
    }
	
	/**
	 * 忽略过滤掉某些关键字的列,关键字不区分大小写,以逗号分隔
	 * @param ignoreKeywords
	 * @return
	 */
	public List<Column> getIgnoreKeywordsColumns(String ignoreKeywords) {
		List results = new ArrayList();
		for(Column c : getColumns()) {
			String sqlname = c.getSqlName().toLowerCase();
			if(StringHelper.contains(sqlname,ignoreKeywords.split(","))) {
				continue;
			}
			results.add(c);
		}
		return results;
	}
	
	/**
	 * This method was created in VisualAge.
	 */
	public void initImportedKeys(DatabaseMetaData dbmd) throws java.sql.SQLException {
		
			   // get imported keys a
	
			   ResultSet fkeys = dbmd.getImportedKeys(catalog,schema,this.sqlName);

			   while ( fkeys.next()) {
				 String pktable = fkeys.getString(PKTABLE_NAME);
				 String pkcol   = fkeys.getString(PKCOLUMN_NAME);
				 String fktable = fkeys.getString(FKTABLE_NAME);
				 String fkcol   = fkeys.getString(FKCOLUMN_NAME);
				 String seq     = fkeys.getString(KEY_SEQ);
				 Integer iseq   = new Integer(seq);
				 getImportedKeys().addForeignKey(pktable,pkcol,fkcol,iseq);
			   }
			   fkeys.close();
	}
	
	
	/**
	 * qiyuchun
	 */
	public void initImportedKeys(RelationInfos relationInfos) throws java.sql.SQLException {
		
		   
		   List<RelationInfo> relationInfoList = relationInfos.getImportRelationInfos();
		   
		   Iterator<RelationInfo> iter = relationInfoList.iterator();
		   int num = 0;
		   while (iter.hasNext()) {
			 RelationInfo  relationInfo = iter.next();
			 String pktable = relationInfo.getPktable();
			 String pkcol   = relationInfo.getPkcol();
			 String fktable = relationInfo.getFktable();
			 String fkcol   = relationInfo.getFkcol();
			 String seq     = relationInfo.getSeq();
			 Integer iseq = 1;
			 if(seq!=null && !seq.equals("")) {
				 iseq   = new Integer(seq);
			 } else {
				 iseq = ++num;
			 }
			
			 getImportedKeys().addForeignKey(pktable,pkcol,fkcol,iseq);
		   }
		   
		   
	}
	
	
	/**
	 * This method was created in VisualAge.
	 */
	public void initExportedKeys(DatabaseMetaData dbmd) throws java.sql.SQLException {
			   // get Exported keys
	
			   ResultSet fkeys = dbmd.getExportedKeys(catalog,schema,this.sqlName);
			  
			   while ( fkeys.next()) {
				 String pktable = fkeys.getString(PKTABLE_NAME);
				 String pkcol   = fkeys.getString(PKCOLUMN_NAME);
				 String fktable = fkeys.getString(FKTABLE_NAME);
				 String fkcol   = fkeys.getString(FKCOLUMN_NAME);
				 String seq     = fkeys.getString(KEY_SEQ);
				 Integer iseq   = new Integer(seq);
				 getExportedKeys().addForeignKey(fktable,fkcol,pkcol,iseq);
			   }
			   fkeys.close();
	}
	
	/**
	 * qiyuchun
	 */
	public void initExportedKeys(RelationInfos relationInfos) throws java.sql.SQLException {
			   // get Exported keys
			   
			   List<RelationInfo> relationInfoList = relationInfos.getExportRelationInfos();
			   
			   Iterator<RelationInfo> iter = relationInfoList.iterator();
			   int num = 0;
			   while (iter.hasNext()) {
				 RelationInfo  relationInfo = iter.next();
				 String pktable = relationInfo.getPktable();
				 String pkcol   = relationInfo.getPkcol();
				 String fktable = relationInfo.getFktable();
				 String fkcol   = relationInfo.getFkcol();
				 String seq     = relationInfo.getSeq();
				 Integer iseq = 1;
				 if(seq!=null && !seq.equals("")) {
					 iseq   = new Integer(seq);
				 } else {
					 iseq = ++num;
				 }
				
				 getExportedKeys().addForeignKey(fktable,fkcol,pkcol,iseq);
			   }

	}
	
	
	/**
	 * @return Returns the exportedKeys.
	 */
	public ForeignKeys getExportedKeys() {
		if (exportedKeys == null) {
			exportedKeys = new ForeignKeys(this);
		}
		return exportedKeys;
	}
	/**
	 * @return Returns the importedKeys.
	 */
	public ForeignKeys getImportedKeys() {
		if (importedKeys == null) {
			importedKeys = new ForeignKeys(this);
		}
		return importedKeys;
	}
	
	public String toString() {
		return "Database Table:"+getSqlName()+" to ClassName:"+getClassName();
	}
	
	String catalog = TableFactory.getInstance().getCatalog();
	String schema = TableFactory.getInstance().getSchema();
	
	private String tableAlias;
	private ForeignKeys exportedKeys;
	private ForeignKeys importedKeys;
	
	public    static final String PKTABLE_NAME  = "PKTABLE_NAME";
	public    static final String PKCOLUMN_NAME = "PKCOLUMN_NAME";
	public    static final String FKTABLE_NAME  = "FKTABLE_NAME";
	public    static final String FKCOLUMN_NAME = "FKCOLUMN_NAME";
	public    static final String KEY_SEQ       = "KEY_SEQ";
}
