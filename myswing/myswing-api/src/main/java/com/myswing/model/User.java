package com.myswing.model;

import java.util.Date;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @version 1.0
 * @author Eric.wang
 * @date 2017/11/01 14:48
 * @email 1595905476(a)qq.com
 */
//
public class User implements Serializable {

	//
	private Long id;
	//用户名名
	private String accountName;
	//密码
	private byte[] password;
	//是否可用
	private Boolean enable;
	//创建时间
	private Timestamp createTime;
	//邮箱地址
	private String email;
	//微信id
	private String wxId;
	

	public User(){
	}
	


	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountName() {
		return this.accountName;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}
	
	public byte[] getPassword() {
		return this.password;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	public Boolean getEnable() {
		return this.enable;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public Timestamp getCreateTime() {
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

