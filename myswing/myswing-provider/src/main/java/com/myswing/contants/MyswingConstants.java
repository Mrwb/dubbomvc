package com.myswing.contants;


public interface MyswingConstants {
	
	final static String avatar_pixel_small= "64x64,1";
	final static String avatar_pixel_medium= "128x128,2";
	final static String avatar_pixel_big= "256x256,3";
	
	final static String avatar_percent_small= "10%x10%,1";
	final static String avatar_percent_medium= "30%x30%,2";
	final static String avatar_percent_big= "50%x50%,3";
	
	final static String article_pixel_small= "360x360,1";
	final static String article_percent_small= "60%x60%,2";
	final static String avatar= "avatar";
	final static String article= "article";
	
	/**已和clinet 同步*/
	static final String CLIENT_SUCCESS_STATUS = "1";
	static final String CLIENT_ERROR_STATUS = "0";
	static final String CLIENT_TOKEN_ERROR = "-1";
	static final String STATUS = "status";
	static final String TOKEN = "token";
	static final String TOKEN_INVALIDATE = "Token Invalidate";
	static final String FIRSTNAME = "firstName";
	static final String LASTNAME = "lastName";
	static final String COACH_ID = "coachID";
	static final int CLIENT_USER_NORMAL = 2;
	static final int CLIENT_USR_PRO = 1;
	// --------------------提示信息---------------------------
	static final String ACCOUNT_CLOSED = "Account Closed";
	static final String ACCOUNT_EXPIRED = "Account Expired";
	static final String DB_OPERATION_FAIL = "DB Operation Fail";
	static final String UPLOAD_FAIL ="Upload Fail";
	static final String SNAPSHOT_EXISTED ="Snapshot Existed";
	static final String SNAPSHOT_FINISHED ="Snapshot Finished";
	static final String SWINGDATA_FINISHED ="Swingdata Finished";
	static final String RADARDATA_FINISHED ="Radardata Finished";
	static final String ACCOUNT_CHANGE ="The account you are using has not been activated,please contact Customer Support at 1-844-843-1270";
	// ----------------------key------------------------------------
	static final String EXPIRETIME = "expire_time";
	static final String SESSIONID = "sessionid";
	static final String PUBLICKEY = "public_key";
	static final String FLG = "flg";
	static final String MSG = "msg";
	static final String DATA = "data";
	static final String EMAIL = "email";
	static final String LINK = "link";
	static final String STUDENT_FIRSTNAME = "student_firstname";
	static final String STUDENT_LASTNAME = "student_lastname";
	static final String COACH_FIRSTNAME = "coach_firstname";
	static final String COACH_LASTNAME = "coach_lastname";
	static final String SEND_NUM = "send_num";
	// -----------------------状态码------------------------------------
	static final String CODE = "code";
	static final String CODE_SUCCESS = "0x010000";
	static final String CODE_DB_OPERATION_FAIL = "0x000001";
	static final String CODE_ACCOUNT_EXPIRED = "0x000010";
	static final String CODE_UPLOAD_FAIL = "0x000100";
	static final String CODE_TOKEN_INVALIDATE = "0x001000";
	static final String CODE_ACCOUNT_CLOSED = "0x010000";
	static final String CODE_SNAPSHOT_EXISTED = "0x100000";
	static final String CODE_SNAPSHOT_FINISHED = "0x100001";
	static final String CODE_SWINGDATA_FINISHED = "0x100010";
	static final String CODE_RADARDATA_FINISHED = "0x100100";
	// 账户版本改变 如Express版升级成Pro
	static final String CODE_ACCOUNT_CHANGE = "0x101000";
	// ------------------------常量------------------------------------
	static final String DELETED_FLG = "deleted";
	static final String RECOVERED_FLG = "recovered";
	
	static final int NONE=0;
	   //添加
	static final int  ADD=1;
	   //编辑
	static final int  EDIT=2;
	   //删除
	static final int  DELETE=3;	
	   //更新
	static final int  DOWN=4;
	   //同步
	static final int  SYNC=5;
	
	
	public static final String IOS = "ios";
	public static final String MAC = "mac";
	public static final String ANDROID = "android";
	public static final String WINTABLET = "wintablet";
	public static final String WINDESKTOP = "windesktop";
	
	/**截图类型*/
	/** 待机的状态 */
	static final int IMAGE_TYPE_Idle=0;
	/** 杆水平*/
	static final int IMAGE_TYPE_CLUB_PLAN=1;
	/**左上臂水平	注意，右撇子（ lead arm为左臂）指左上臂水平， 如果加上左撇子（ lead arm为右臂）指右上臂水平*/
	static final int IMAGE_TYPE_LFORE_ARM_PLAN=2;
	/**杆到最大位置（速度为0，下一帧高度开始减）；*/
	static final int IMAGE_TYPE_CLUB_HEIGHT_MAX=3;
     /**左上臂水平，并且是返回的状态*/
	static final int IMAGE_TYPE_LForeArmPlanBack=4;
    /** 杆水平 */
	static final int IMAGE_TYPE_ClubPlanBack=5;
    /** 杆垂直 */
	static final int IMAGE_TYPE_ClubVertical=6;
    /** 杆结束时候的水平*/
	static final int IMAGE_TYPE_ClubPlanOver=7;
	/** 右上臂水平 注意，右撇子（ lead arm为左臂）指右上臂水平， 如果加上左撇子（ lead arm为右臂）指左上臂水平*/
	static final int IMAGE_TYPE_RForeArmPlan=8;
    /** 杆到最大位置（速度为0）； */
	static final int IMAGE_TYPE_ClubHeightEndMax=9;
    /** 结束状态 */
	static final int IMAGE_TYPE_End=10;
	/** 初始化状态*/
	static final String IMAGE_TYPE_NULL="11";
	
	static final int SWINGDATA_STATUS_FINISH=1;
	
	static final int SWINGDATA_STATUS_UPLOADING=0;
	
	static final int SNAPSHOT_STATUS_FINISH=1;
	
	static final int SNAPSHOT_STATUS_UPLOADING=0;
	
	static final int RADAR_STATUS_FINISH=1;
	
	static final int RADAR_STATUS_UPLOADING=0;
	
	static final int RADAR_TYPE_NONE=0;
	
	static final int COACH_STATUS_ENABLE=1;
	// 过期时间偏移量
	static final int COACH_EXPIRE_TIME=2;
	
	/**softwrea version 常量*/
	static final String PRO="pro";
	static final String EXPRESS="express";
	static final String TRAINER="trainer";
	
}
