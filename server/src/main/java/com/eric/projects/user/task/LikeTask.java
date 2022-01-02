package com.eric.projects.user.task;

import com.eric.projects.user.service.LikedService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class LikeTask extends QuartzJobBean {

    @Autowired
    LikedService likedService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("LikeTask---------- {}", sdf.format(new Date()));

        likedService.transLikedFromRedis2DB();
    }
}
