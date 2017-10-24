
package com.myswing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myswing.dao.MybatisDao;
import com.myswing.model.VersionUpgrade;
/**
 * @version 1.1
 * @author wangbo
 * @date 2015-05-05 16:02
 * @email 1595905476(a)qq.com
 */
@Service
public class VersionUpgradeBaseService  {
	
	@Resource(name = "dao")
	private MybatisDao dao;
	/**
	 * 保存VersionUpgrade对象
	 * @param VersionUpgrade
	 * @return 影响条数
	 */
	public int saveVersionUpgrade(VersionUpgrade versionUpgrade){
		return dao.insertObj("VersionUpgradeMapper.insert", versionUpgrade);
	}
	
	/**
	 * 删除VersionUpgrade对象
	 * @param VersionUpgrade的ID
	 * @return 影响条数
	 */
	public int deleteVersionUpgrade(String id){
		return dao.deleteObj("VersionUpgradeMapper.deleteById", id);
	}
	
	/**
	 * 修改VersionUpgrade对象
	 * @param VersionUpgrade
	 * @return 影响条数
	 */
	public int updateVersionUpgrade(VersionUpgrade versionUpgrade){
		return dao.updateObj("VersionUpgradeMapper.updateById", versionUpgrade);
	}
	
	/**
	 * 获取VersionUpgrade对象
	 * @param VersionUpgrade的ID
	 * @return VersionUpgrade对象
	 */
	public VersionUpgrade getVersionUpgrade(String id){
		return (VersionUpgrade)dao.queryForId("VersionUpgradeMapper.selectById", id);
	}
	
	
	/**
	 * 获取VersionUpgrade对象集合
	 * @param VersionUpgrade
	 * @return VersionUpgrade对象集合
	 */
	public List<VersionUpgrade> findVersionUpgrade(VersionUpgrade versionUpgrade){
		List<VersionUpgrade> list = (List<VersionUpgrade>)dao.queryForList("VersionUpgradeMapper.selectByConditionAll", versionUpgrade);
		return list;
	}
	
	/**
	 * 获取VersionUpgrade对象
	 * @param VersionUpgrade
	 * @return VersionUpgrade对象
	 */
	public VersionUpgrade findOneVersionUpgrade(VersionUpgrade versionUpgrade){
		VersionUpgrade tmpversionUpgrade = (VersionUpgrade) dao.queryForObject("VersionUpgradeMapper.selectOneByCondition", versionUpgrade);
		return tmpversionUpgrade;
	}
}