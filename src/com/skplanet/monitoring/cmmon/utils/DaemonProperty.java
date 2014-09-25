package com.skplanet.monitoring.cmmon.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DaemonProperty {
	private Properties properties = null;
	public String properties_dir="";
	
	public DaemonProperty() {
		properties = new Properties();
		FileInputStream file = null;
		String profDir = System.getProperty("ProfDir");
		String profFile = profDir+"/properties.ini";
				
		try {
			file = new FileInputStream(profFile);
			properties.load(file);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public void setProperty(Properties properties) {
		this.properties = properties;
	}
}
