package com.cbr.cbrdemoone.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by summer on 2016/12/1.
 */

@Component
public class SchedulerTask {

    private int count=0;

    //@Scheduled 参数可以接受两种定时的设置，
    //一种是我们常用的cron="*/6 * * * * ?",
    //一种是 fixedRate = 6000，
    // 两种都表示每隔六秒打印一下内容
    @Scheduled(cron="*/60 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }

}
