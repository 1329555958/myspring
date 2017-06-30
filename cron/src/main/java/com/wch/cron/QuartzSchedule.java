package com.wch.cron;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by weichunhe on 2016/5/20.
 */
//@Configuration
public class QuartzSchedule implements Job {
    private static Logger log = LoggerFactory.getLogger(QuartzSchedule.class);

    private static final String DEFAULT_GROUP = "default";

    @Bean(name = "createJob")
    public JobDetailFactoryBean createJob() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setName("test");
        factoryBean.setDescription("测试quartz任务");
        factoryBean.setDurability(true);
        factoryBean.setGroup(DEFAULT_GROUP);
        factoryBean.setJobClass(QuartzSchedule.class);
        return factoryBean;
    }

    @Bean
    public CronTriggerFactoryBean createTrigger(@Qualifier("createJob") JobDetailFactoryBean jobFactory) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setDescription("测试trigger");
        factoryBean.setName("trigger");
        factoryBean.setGroup(DEFAULT_GROUP);
        factoryBean.setJobDetail(jobFactory.getObject());
        factoryBean.setCronExpression("5 * * * * ?");
        factoryBean.setStartDelay(0L);
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean createSchedule(CronTriggerFactoryBean cronTriggerFactoryBean) throws SchedulerException {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setTriggers(cronTriggerFactoryBean.getObject());
        return factoryBean;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("quartz schedule!");
    }
}
