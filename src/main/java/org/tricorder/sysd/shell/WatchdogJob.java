package org.tricorder.sysd.shell;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class WatchdogJob implements Job {
	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		Auditor auditor = (Auditor) ctx.getJobDetail().getJobDataMap().get("auditor");
		
		if (auditor.allGood())
			new org.tricorder.sysd.shell.systemdnotify().pingWatchdog();

	 }

}