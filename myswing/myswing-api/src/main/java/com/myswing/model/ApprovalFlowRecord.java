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
public class ApprovalFlowRecord implements Serializable {

	//唯一id
	private Long id;
	//审批表单id
	private Long approvalInfoId;
	//审批表单信息
	private String approvalFormInfo;
	//审批信息版本号格式201709201639001
	private String approvalVersion;
	//审批表单内容
	private String approvalFormContent;
	//审批流程规则(包含审批流程信息/审批抄送人信息)
	private String flowRule;
	//
	private String ccerRoleIds;
	//申请人id
	private Long proposerId;
	//申请人姓名
	private String proposerName;
	//部门名称
	private String departmentName;
	//审批类型 0 通用 1 项目
	private Integer approvalCategory;
	//审批状态 0 审批中 1 审批通过 2 审批未通过 3 已撤销
	private Integer approvalStatus;
	//审批验证密匙
	private String approvalKey;
	//创建时间
	private Timestamp createTime;
	

	public ApprovalFlowRecord(){
	}
	


	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setApprovalInfoId(Long approvalInfoId) {
		this.approvalInfoId = approvalInfoId;
	}
	
	public Long getApprovalInfoId() {
		return this.approvalInfoId;
	}

	public void setApprovalFormInfo(String approvalFormInfo) {
		this.approvalFormInfo = approvalFormInfo;
	}
	
	public String getApprovalFormInfo() {
		return this.approvalFormInfo;
	}

	public void setApprovalVersion(String approvalVersion) {
		this.approvalVersion = approvalVersion;
	}
	
	public String getApprovalVersion() {
		return this.approvalVersion;
	}

	public void setApprovalFormContent(String approvalFormContent) {
		this.approvalFormContent = approvalFormContent;
	}
	
	public String getApprovalFormContent() {
		return this.approvalFormContent;
	}

	public void setFlowRule(String flowRule) {
		this.flowRule = flowRule;
	}
	
	public String getFlowRule() {
		return this.flowRule;
	}

	public void setCcerRoleIds(String ccerRoleIds) {
		this.ccerRoleIds = ccerRoleIds;
	}
	
	public String getCcerRoleIds() {
		return this.ccerRoleIds;
	}

	public void setProposerId(Long proposerId) {
		this.proposerId = proposerId;
	}
	
	public Long getProposerId() {
		return this.proposerId;
	}

	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}
	
	public String getProposerName() {
		return this.proposerName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setApprovalCategory(Integer approvalCategory) {
		this.approvalCategory = approvalCategory;
	}
	
	public Integer getApprovalCategory() {
		return this.approvalCategory;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public Integer getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalKey(String approvalKey) {
		this.approvalKey = approvalKey;
	}
	
	public String getApprovalKey() {
		return this.approvalKey;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public Timestamp getCreateTime() {
		return this.createTime;
	}



}

