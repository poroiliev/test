package com.home.reactor.scheduling;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

@Configuration
public class SchedulerConfig {

//    @Bean
//    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
//        return (schedulerFactoryBean) -> {
//        };
//    }

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob()
                .ofType(Casio.class)
                .withDescription("test description")
                .storeDurably(true)
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("Qrtz_Trigger")
                .withDescription("Sample trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInMilliseconds(1000L))
                .forJob(jobDetail)
                .build();
    }

    @Bean(name = "2")
    public JobDetail jobDetail2() {
        return JobBuilder.newJob()
                .ofType(Casio.class)
                .withDescription("test description")
                .storeDurably(true)
                .build();
    }

    @Bean
    public Trigger trigger2(@Qualifier() JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("Qrtz_Trigger")
                .withDescription("Sample trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInMilliseconds(100L))
                .forJob(jobDetail)
                .build();
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail jobDetail) throws IOException, SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
//        factory.initialize(new ClassPathResource("quartz.properties").getInputStream());

        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();
        return scheduler;
    }

}
