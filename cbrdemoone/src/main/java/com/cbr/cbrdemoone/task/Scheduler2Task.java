package com.cbr.cbrdemoone.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by summer on 2016/12/1.
 *
 */

@Component
public class Scheduler2Task {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled 参数可以接受两种定时的设置，
    //一种是我们常用的cron="*/6 * * * * ?", 六秒
    //一种是 fixedRate = 6000， 六秒
    // 两种都表示每隔六秒打印一下内容
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

}
