package com.myswing.service;
import com.myswing.model.ApprovalFlowRecord;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.myswing.dao.MybatisDao;
import java.util.List;
import com.myswing.page.ApprovalFlowRecordPage;

/**
 * @version 1.0
 * @author Eric.wang
 * @date 2017/11/01 14:48
 * @email 1595905476(a)qq.com
 */
@Service
public class ApprovalFlowRecordBaseService  {
	
	@Resource(name = "dao")
	private MybatisDao dao;
	/**
	 * 保存ApprovalFlowRecord对象
	 * @param ApprovalFlowRecord
	 * @return 影响条数
	 */
	public int saveApprovalFlowRecord(ApprovalFlowRecord approvalFlowRecord){
		return dao.insertObj("ApprovalFlowRecordMapper.insert", approvalFlowRecord);
	}
	
	/**
	 * 删除ApprovalFlowRecord对象
	 * @param ApprovalFlowRecord的ID
	 * @return 影响条数
	 */
	public int deleteApprovalFlowRecord(String id){
		return dao.deleteObjById("ApprovalFlowRecordMapper.deleteById", id);
	}
	
	/**
	 * 修改ApprovalFlowRecord对象
	 * @param ApprovalFlowRecord
	 * @return 影响条数
	 */
	public int updateApprovalFlowRecord(ApprovalFlowRecord approvalFlowRecord){
		return dao.updateObj("ApprovalFlowRecordMapper.updateById", approvalFlowRecord);
	}
	
	/**
	 * 获取ApprovalFlowRecord对象
	 * @param ApprovalFlowRecord的ID
	 * @return ApprovalFlowRecord对象
	 */
	public ApprovalFlowRecord getApprovalFlowRecord(String id){
		return (ApprovalFlowRecord)dao.queryForId("ApprovalFlowRecordMapper.selectById", id);
	}
	
	/**
	 * 获取ApprovalFlowRecord对象集合
	 * @param Page
	 * @return ApprovalFlowRecord对象集合
	 */
	public ApprovalFlowRecordPage findApprovalFlowRecord(ApprovalFlowRecordPage approvalFlowRecordPage){
		int pagecount = (int) dao.queryForCount("ApprovalFlowRecordMapper.findCount",approvalFlowRecordPage);
		approvalFlowRecordPage.setTotalNum(pagecount);
		List<ApprovalFlowRecord> list = (List<ApprovalFlowRecord>)dao.queryForList("ApprovalFlowRecordMapper.selectByCondition", approvalFlowRecordPage);
		approvalFlowRecordPage.setList(list);
		return approvalFlowRecordPage;
	}
	
	/**
	 * 获取ApprovalFlowRecord对象集合
	 * @param ApprovalFlowRecord
	 * @return ApprovalFlowRecord对象集合
	 */
	public List<ApprovalFlowRecord> findApprovalFlowRecord(ApprovalFlowRecord approvalFlowRecord){
		List<ApprovalFlowRecord> list = (List<ApprovalFlowRecord>)dao.queryForList("ApprovalFlowRecordMapper.selectByConditionAll", approvalFlowRecord);
		return list;
	}
	
	/**
	 * 获取ApprovalFlowRecord对象
	 * @param ApprovalFlowRecord
	 * @return ApprovalFlowRecord对象
	 */
	public ApprovalFlowRecord findOneApprovalFlowRecord(ApprovalFlowRecord approvalFlowRecord){
		ApprovalFlowRecord tmpapprovalFlowRecord = (ApprovalFlowRecord) dao.queryForObject("ApprovalFlowRecordMapper.selectOneByCondition", approvalFlowRecord);
		return tmpapprovalFlowRecord;
	}
}