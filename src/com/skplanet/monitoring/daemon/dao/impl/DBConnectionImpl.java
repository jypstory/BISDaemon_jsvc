package com.skplanet.monitoring.daemon.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skplanet.monitoring.cmmon.utils.DaemonProperty;
import com.skplanet.monitoring.daemon.dao.DBConnection;
import com.skplanet.monitoring.daemon.service.impl.MainDaemonImpl;

public class DBConnectionImpl implements DBConnection{

	Logger logger = LoggerFactory.getLogger(MainDaemonImpl.class);
	private static DaemonProperty prof = new DaemonProperty();

	@Override
	public Connection getConnection(String targetDb) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url ="";
		String id = "";
		String pw =  "";

		if(targetDb.equals("ASDB")){
			logger.info("try Connect ASDB");
			url = prof.getProperty("asdb_url");
			id = prof.getProperty("asdb_id");
			pw = prof.getProperty("asdb_pw");

		}else if(targetDb.equals("EXADB")){
			logger.info("try Connect EXADB");
			url = prof.getProperty("exadb_url");
			id = prof.getProperty("exadb_id");
			pw = prof.getProperty("exadb_pw");

		}else {
			logger.error("Connection Db is not exists...");
		}

		try {
			con = DriverManager.getConnection(url,id,pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//con.close();
		}

		return con;

	}
}
