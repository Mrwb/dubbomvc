package com.noitom.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import ${basepackage}.contants.Constants;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JSON implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8196453207907708910L;
	public String code = Constants.CODE_GENERAL_FAIL;
	public String message = Constants.MSG_GENERAL_FAIL;
	public Object data;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
