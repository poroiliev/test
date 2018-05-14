package com.home.reactor.scheduling;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

// It is component. Use setter injection.
@DisallowConcurrentExecution
public class Casio extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("!!!!!!!!!!!!!!!!!!");
    }
}
