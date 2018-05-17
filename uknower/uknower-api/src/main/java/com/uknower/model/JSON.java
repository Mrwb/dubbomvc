package com.uknower.model;

import java.io.Serializable;
import com.uknower.constant.Constants;

public class JSON implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8196453207907708910L;
	public String code = Constants.CODE_GENERAL_SUCCESS;
	public String message = Constants.MSG_GENERAL_SUCCESS;
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
