package org.tricorder.sysd.shell;

import java.io.IOException;
import java.lang.management.ManagementFactory;

public class systemdnotify {

	public String pingWatchdog() {
        String pidStr = ManagementFactory.getRuntimeMXBean().getName();
        pidStr = pidStr.split("@")[0];
        String location = System.getenv().get("FILELOC");
        if (!location.contains("systemd-notify"))
        	location = "systemd-notify";
          
		try {
			Process process = new ProcessBuilder(location, "MAINPID=" + pidStr, "WATCHDOG=1").start();	
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return "Notified";
	}

}
