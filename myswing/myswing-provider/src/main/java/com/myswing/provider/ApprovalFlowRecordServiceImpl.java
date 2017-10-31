
package com.myswing.provider;

import org.springframework.beans.factory.annotation.Autowired;
import com.myswing.model.ApprovalFlowRecord;
import com.myswing.api.ApprovalFlowRecordService;
import com.myswing.service.ApprovalFlowRecordBaseService;
/**
 * @version 1.0
 * @author Eric.wang
 * @date 2017/11/01 14:48
 * @email 1595905476(a)qq.com
 */
public class ApprovalFlowRecordServiceImpl implements ApprovalFlowRecordService  {

	@Autowired
	private ApprovalFlowRecordBaseService approvalFlowRecordBaseService;
	
	public String queryApprovalFlowRecord() {
		ApprovalFlowRecord approvalFlowRecordtmp = new ApprovalFlowRecord();
		approvalFlowRecordtmp.setId(61L);
		ApprovalFlowRecord approvalFlowRecord = approvalFlowRecordBaseService.findOneApprovalFlowRecord(approvalFlowRecordtmp);
	    return "answer is "+approvalFlowRecord.getProposerName();
	}
}