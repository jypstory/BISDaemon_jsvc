package com.skplanet.monitoring.daemon.service.impl;

import java.io.*;

import com.skplanet.monitoring.daemon.service.CheckFileExists;

public class CheckFileExistsImpl implements CheckFileExists{

	@Override
	public boolean checkFileExists(String fileDir) {
		// TODO Auto-generated method stub

		File f = new File("test.txt");
		return f.isFile();
		
	}

}
