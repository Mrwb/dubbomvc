package com.uknower.model.user;

import java.util.Date;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @version 1.0
 * @author Eric.wang
 * @date 2018/05/16 11:48
 * @email 1595905476(a)qq.com
 */
//
public class User implements Serializable {

	//
	private Integer id;
	//
	private String accountName;
	//
	private String password;
	//
	private String enable;
	//
	private String createTime;
	//
	private String email;
	//
	private String wxId;
	

	public User(){
	}
	


	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountName() {
		return this.accountName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	public String getEnable() {
		return this.enable;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getCreateTime() {
		return this.createTime;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}
	
	public String getWxId() {
		return this.wxId;
	}



}

