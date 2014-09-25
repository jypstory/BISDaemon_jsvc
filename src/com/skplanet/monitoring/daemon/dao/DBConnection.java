package com.skplanet.monitoring.daemon.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {

	public Connection getConnection(String targetDb) throws ClassNotFoundException, SQLException;

	
	
}
