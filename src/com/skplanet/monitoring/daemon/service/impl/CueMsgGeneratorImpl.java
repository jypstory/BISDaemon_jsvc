package com.skplanet.monitoring.daemon.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skplanet.monitoring.daemon.service.CueMsgGenerator;
import com.skplanet.monitoring.daemon.vo.CueOutputVo;

public class CueMsgGeneratorImpl implements CueMsgGenerator {

	Logger logger = LoggerFactory.getLogger(MainDaemonImpl.class);

	final String DELEMETER =",";

	@Override
	public String makeCueMsg(CueOutputVo cvo) {
		// TODO Auto-generated method stub

		String fullMsg=""; 
		fullMsg += "<cuemsg id="+cvo.getCuemsgId()+">";
		fullMsg += "{ErrorCode="+cvo.getErrorCode()+DELEMETER;
		fullMsg += "Message="+ (cvo.getMessage()==null ? "" : "["+ cvo.getErrorType() +"]["+ cvo.getCuemsgId() +"]["+ cvo.getJobName() +"]["+ cvo.getEntity() +"]") + DELEMETER;
		fullMsg += "ErrorType="+(cvo.getErrorType()==null ? "" : cvo.getErrorType()) +DELEMETER;
		fullMsg += "OriginalMessage="+(cvo.getOriginalMessage()==null ? "" : cvo.getOriginalMessage())+DELEMETER;
		fullMsg += "JobID="+cvo.getJobID()+DELEMETER;
		fullMsg += "JobName="+cvo.getJobName()+DELEMETER;
		fullMsg += "Entity="+cvo.getEntity()+DELEMETER;
		fullMsg += "Start="+cvo.getStart()+DELEMETER;
		fullMsg += "End="+cvo.getEnd()+DELEMETER;
		fullMsg += "Count="+(cvo.getCount()==null ? "" : cvo.getCount()) +DELEMETER;
		fullMsg += "Insert="+(cvo.getInsert()==null ? "" : cvo.getInsert()) +DELEMETER;
		fullMsg += "Update="+(cvo.getUpdate()==null ? "" : cvo.getUpdate()) +DELEMETER;
		fullMsg += "Delete="+(cvo.getDelete()==null ? "" : cvo.getDelete()) +DELEMETER;
		fullMsg += "LogTime="+cvo.getLogTime()+"}";
		fullMsg += "\r\n";


		logger.info("fullMsg= "+fullMsg);

		return fullMsg;
	}

	@Override
	public String makeCueMsg(ArrayList<CueOutputVo> cueList) {
		// TODO Auto-generated method stub

		String fullMsg=""; 
		for (CueOutputVo cvo : cueList) {
		fullMsg += "<cuemsg id="+cvo.getCuemsgId()+">";
		fullMsg += "{ErrorCode="+cvo.getErrorCode()+DELEMETER;
		fullMsg += "Message="+ (cvo.getMessage()==null ? "" : "["+ cvo.getErrorType() +"]["+ cvo.getCuemsgId() +"]["+ cvo.getJobName() +"]["+ cvo.getEntity() +"]") + DELEMETER;
		fullMsg += "ErrorType="+(cvo.getErrorType()==null ? "" : cvo.getErrorType()) +DELEMETER;
		fullMsg += "OriginalMessage="+(cvo.getOriginalMessage()==null ? "" : cvo.getOriginalMessage())+DELEMETER;
		fullMsg += "JobID="+cvo.getJobID()+DELEMETER;
		fullMsg += "JobName="+cvo.getJobName()+DELEMETER;
		fullMsg += "Entity="+cvo.getEntity()+DELEMETER;
		fullMsg += "Start="+cvo.getStart()+DELEMETER;
		fullMsg += "End="+cvo.getEnd()+DELEMETER;
		fullMsg += "Count="+(cvo.getCount()==null ? "" : cvo.getCount()) +DELEMETER;
		fullMsg += "Insert="+(cvo.getInsert()==null ? "" : cvo.getInsert()) +DELEMETER;
		fullMsg += "Update="+(cvo.getUpdate()==null ? "" : cvo.getUpdate()) +DELEMETER;
		fullMsg += "Delete="+(cvo.getDelete()==null ? "" : cvo.getDelete()) +DELEMETER;
		fullMsg += "LogTime="+cvo.getLogTime()+"}";
		fullMsg += "\r\n";
		}

		logger.info("fullMsg= "+fullMsg);

		return fullMsg;
	}

}
