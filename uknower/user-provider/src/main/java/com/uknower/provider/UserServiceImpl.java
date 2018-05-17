
package com.uknower.provider;

import com.uknower.api.user.UserService;
import com.uknower.core.SumNumber;
import com.uknower.model.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.uknower.model.user.User;
import com.uknower.service.UserBaseService;
/**
 * @version 1.0
 * @author Eric.wang
 * @date 2018/05/16 14:48
 * @email 1595905476(a)qq.com
 */
public class UserServiceImpl implements UserService {

	@Autowired
	private UserBaseService userBaseService;

	public JSON query() {
		JSON json = new JSON();
		User usertmp = new User();
		usertmp.setId(1);
		User user = userBaseService.findOneUser(usertmp);
		SumNumber sn = new SumNumber();
		json.setData(user);
	    return json;
	}
}