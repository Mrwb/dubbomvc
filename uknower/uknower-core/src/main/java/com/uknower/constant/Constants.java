package com.uknower.constant;


public interface Constants {

	static final String CLIENT_SUCCESS_STATUS = "1";
	static final String CLIENT_ERROR_STATUS = "0";
	static final String CLIENT_TOKEN_ERROR = "-1";
	static final String STATUS = "status";
	static final String TOKEN = "token";
	static final String TOKEN_INVALIDATE = "Token Invalidate";

	//-----------------------状态码------------------------------------
	static final String CODE = "code";
	static final String CODE_GENERAL_FAIL = "0x000000";
	static final String CODE_GENERAL_SUCCESS = "0x010000";
	static final String CODE_DB_OPERATION_FAIL = "0x000001";
	static final String CODE_ACCOUNT_EXPIRED = "0x000010";
	static final String CODE_UPLOAD_FAIL = "0x000100";
	static final String CODE_TOKEN_INVALIDATE = "0x001000";
	static final String CODE_ACCOUNT_CLOSED = "0x010000";
	static final String CODE_ACCOUNT_CHANGE = "0x101000";

	//-----------------------消息------------------------------------
	static final String MSG_GENERAL_FAIL = "执行失败";
	static final String MSG_GENERAL_SUCCESS = "执行成功";
	
}
