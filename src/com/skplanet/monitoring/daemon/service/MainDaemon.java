package com.skplanet.monitoring.daemon.service;

import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

public interface MainDaemon {

	public void init(DaemonContext context) throws DaemonInitException;

	public void start() throws Exception ;
	public void stop() throws Exception ;
	public void destroy() throws Exception ;  
	public void run() throws Exception ;
}
