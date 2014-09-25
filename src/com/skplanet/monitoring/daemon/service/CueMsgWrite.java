package com.skplanet.monitoring.daemon.service;

import java.util.ArrayList;

import com.skplanet.monitoring.daemon.vo.CueOutputVo;

public interface CueMsgWrite {
	//public void writeCue(CueOutputVo cvo);
	public ArrayList<CueOutputVo> writeCue(ArrayList<CueOutputVo> cueList);
	
}
