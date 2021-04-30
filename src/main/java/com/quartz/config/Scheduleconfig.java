package com.quartz.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quartz.job.QuartzJob;;

@Configuration
public class Scheduleconfig
{
	@Qualifier("SchedulerB")
	@Autowired
	private Scheduler schedulerB;
	
	@Bean
	public JobDetail scheduleBJobDetail()
	{
		return JobBuilder.newJob(QuartzJob.class).withIdentity("scheduleJobB").storeDurably().build();
	}
	
	@Bean
	public Trigger scheduleBJobTrigger()
	{
		// cron
		CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule("0/20 * * * * ?");
		
		return TriggerBuilder.newTrigger().withSchedule(cronBuilder).build();
	}
	
	@Bean
	public void addscheduleB() throws SchedulerException
	{
		JobDetail jobDetail = scheduleBJobDetail();
		
		if (schedulerB.checkExists(jobDetail.getKey()))
		{
			schedulerB.deleteJob(jobDetail.getKey());
		}
		schedulerB.scheduleJob(jobDetail, scheduleBJobTrigger());
	}
	
}
