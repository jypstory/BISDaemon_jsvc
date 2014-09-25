package com.skplanet.monitoring.daemon.service.impl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skplanet.monitoring.daemon.service.CueMsgWrite;
import com.skplanet.monitoring.daemon.vo.CueOutputVo;


public class CueMsgWriteImpl implements CueMsgWrite 
{
	@Override
	public ArrayList<CueOutputVo> writeCue(ArrayList<CueOutputVo> cueList) {
		@SuppressWarnings("unused")
		Logger logger = LoggerFactory.getLogger(MainDaemonImpl.class);
		// TODO Auto-generated method stub

		CueMsgGeneratorImpl cmg = new CueMsgGeneratorImpl();
		String fullMsg = cmg.makeCueMsg(cueList);
		String outDir = System.getProperty("CueOutputDir");
		String fileName = "";

		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd"); 
		Date d = gc.getTime();
		String today = sf.format(d);

		if(outDir.equals("")){
			fileName=System.getProperty("user.dir")+"/cueoutput_"+today+".txt";
		} else {
			fileName=outDir+"/cueoutput_"+today+".txt";
		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
			writer.write(fullMsg);
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
