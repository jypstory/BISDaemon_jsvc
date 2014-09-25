package com.skplanet.monitoring.daemon.service;

import java.sql.SQLException;

public interface CheckDbInfo {

	public void getDbInfo(String targetDb) throws SQLException;
	
	
	
}
