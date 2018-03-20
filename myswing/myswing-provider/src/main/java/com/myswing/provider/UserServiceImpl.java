
package com.myswing.provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.myswing.model.User;
import com.myswing.api.UserService;
import com.myswing.service.UserBaseService;
/**
 * @version 1.0
 * @author Eric.wang
 * @date 2017/11/01 14:48
 * @email 1595905476(a)qq.com
 */
public class UserServiceImpl implements UserService  {

	@Autowired
	private UserBaseService userBaseService;
	
	public String queryUser() {
		User usertmp = new User();
		usertmp.setId(18477475642212352L);
		User user = userBaseService.findOneUser(usertmp);
/*		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	    return "answer is "+user.getEmail();
	}
}