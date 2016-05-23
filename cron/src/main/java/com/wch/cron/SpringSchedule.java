package com.wch.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by weichunhe on 2016/5/20.
 */
@Component
public class SpringSchedule {
    private static Logger log = LoggerFactory.getLogger(SpringSchedule.class);

    @Scheduled(cron = "1 * * * * ?")
    public void schedule(){
        log.info("spring schedule!");
    }
}
