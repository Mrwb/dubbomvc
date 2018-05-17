package com.uknower.service;
import com.uknower.model.user.User;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.uknower.dao.MybatisDao;
import java.util.List;
import com.uknower.page.user.UserPage;

/**
 * @version 1.0
 * @author Eric.wang
 * @date 2018/05/16 11:48
 * @email 1595905476(a)qq.com
 */
@Service
public class UserBaseService  {

	@Resource(name = "dao")
	private MybatisDao dao;
	/**
	 * 保存User对象
	 * @param User
	 * @return 影响条数
	 */
	public int saveUser(User user){
		return dao.insertObj("UserMapper.insert", user);
	}

	/**
	 * 删除User对象
	 * @param User的ID
	 * @return 影响条数
	 */
	public int deleteUser(String id){
		return dao.deleteObjById("UserMapper.deleteById", id);
	}

	/**
	 * 修改User对象
	 * @param User
	 * @return 影响条数
	 */
	public int updateUser(User user){
		return dao.updateObj("UserMapper.updateById", user);
	}

	/**
	 * 获取User对象
	 * @param User的ID
	 * @return User对象
	 */
	public User getUser(String id){
		return (User)dao.queryForId("UserMapper.selectById", id);
	}

	/**
	 * 获取User对象集合
	 * @param Page
	 * @return User对象集合
	 */
	public UserPage findUser(UserPage userPage){
		int pagecount = (int) dao.queryForCount("UserMapper.findCount",userPage);
		userPage.setTotalNum(pagecount);
		List<User> list = (List<User>)dao.queryForList("UserMapper.selectByCondition", userPage);
		userPage.setList(list);
		return userPage;
	}

	/**
	 * 获取User对象集合
	 * @param User
	 * @return User对象集合
	 */
	public List<User> findUser(User user){
		List<User> list = (List<User>)dao.queryForList("UserMapper.selectByConditionAll", user);
		return list;
	}

	/**
	 * 获取User对象
	 * @param User
	 * @return User对象
	 */
	public User findOneUser(User user){
		User tmpuser = (User) dao.queryForObject("UserMapper.selectOneByCondition", user);
		return tmpuser;
	}
}