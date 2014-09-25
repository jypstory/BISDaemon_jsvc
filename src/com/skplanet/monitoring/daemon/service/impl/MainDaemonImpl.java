package com.skplanet.monitoring.daemon.service.impl;

import java.sql.SQLException;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skplanet.monitoring.cmmon.utils.DaemonProperty;
import com.skplanet.monitoring.daemon.service.MainDaemon;

public class MainDaemonImpl implements MainDaemon, Daemon, Runnable {

	Logger logger = LoggerFactory.getLogger(MainDaemonImpl.class);
	
	private String status = "";
	private Thread thread = null;
	private static DaemonProperty prof = new DaemonProperty();
	final long SLEEP_TIME = Long.parseLong(prof.getProperty("sleep_time(min)"))*1000*60;

	public String userDir ="";
	
	CheckDbInfoImpl cdi = new CheckDbInfoImpl();

	@Override
	public void init(DaemonContext context) throws DaemonInitException{
		/*logger.error("Daemon error...");
		logger.debug("Daemon debug...");
		logger.info("Daemon info...");*/
		logger.info("BISSmsDaemon start...");
		//String[] args = context.getArguments();
		/*if (args != null) {
			for (String arg : args) {
				System.out.println(arg);
			}
		}*/

		//userDir = System.getProperty("user.dir");
		//logger.info("userDir = "+userDir);

		status = "INITED";
		this.thread = new Thread(this);
		System.out.println("init OK.");
		System.out.println();
	}

	@Override
	public void start() throws Exception  {
		System.out.println("status: " + status);
		status = "STARTED";
		this.thread.start();
		System.out.println("start OK.");
		System.out.println();
	}

	@Override
	public void stop() throws Exception {
		System.out.println("status: " + status);
		status = "STOPED";
		this.thread.join(10);
		System.out.println("stop OK.");
		System.out.println();
	}

	@Override
	public void destroy()  { 
		System.out.println("status: " + status);
		System.out.println("destroy...");
		status = "DESTROIED";
		System.out.println("destroy OK."); 
		System.out.println();
	}

	@Override
	public void run(){
		while (true) {
			try {
			
				cdi.getDbInfo("EXADB");
				
				Thread.sleep(SLEEP_TIME);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
