package ${basepackage}.contants;


public interface Constants {
	// ----------- CODE ----------------------------
	final static String CODE_GENERAL_SUCCESS = "0x001";
	final static String CODE_GENERAL_FAIL = "0x002";
	final static String CODE_LOGIN_FAIL = "0x003";
	// ----------- MESSAGE -------------------------
	final static String MSG_GENERAL_FAIL = "未设置失败消息";
	//-----------login message---------
	final static String ACCOUNT_NOT_EXIST = "Account does not exist";
	final static String LOGIN_ERROR="login error";
	final static String PARAMETER_IS_NULL = "parameter is null";
	final static String TOKEN_FAILURE = "Token failure";
	final static String SUCCESSFUL_OPERATION = "Successful operation";
	final static String LOGGED_IN = "logged in";
	
	final static String OPERATION_FAILURE = "operation failure";
	final static String NOT_UPDATE = "not update";
	final static String TOKEN_KEY = "token";
	final static String PAGE_NUM ="page_num";
	final static String SERIAL_NUMBER ="serial_number";
	final static String AGE ="age";
	final static String OPERATION_DATE ="operation_date";
	final static String PHONE ="phone";
	final static String UTF_8 ="UTF-8";
	
	final static String VERSIONUPGRADE_EMPTY="version is empty";
	static final String SESSIONID = "sessionid";
	static final String PUBLICKEY = "public_key";
	static final String EXPIRETIME = "expire_time";
	// 过期时间偏移量
	static final int COACH_EXPIRE_TIME=60;
}
