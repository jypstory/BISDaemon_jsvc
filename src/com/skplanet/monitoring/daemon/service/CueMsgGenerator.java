package com.skplanet.monitoring.daemon.service;

import java.util.ArrayList;

import com.skplanet.monitoring.daemon.vo.CueOutputVo;

public interface CueMsgGenerator {

	public String makeCueMsg(CueOutputVo cvo);
	public String makeCueMsg(ArrayList<CueOutputVo> cueList);
	
}
