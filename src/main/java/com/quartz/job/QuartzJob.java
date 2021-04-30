package com.quartz.job;

import java.time.LocalDateTime;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QuartzJob extends QuartzJobBean {
	
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    	log.info("Scheduler B 실행 중 : {}", LocalDateTime.now().toString());
    }
}