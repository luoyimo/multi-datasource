package com.noral.multidatasource.configuration.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author hu
 * @Description:
 * @Date Create In 15:01 2019/1/28 0028
 */

@Component
public class RefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    @Scheduled(initialDelay = 1000, fixedDelay = 2000)
    public void set() {
        System.out.println("a");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
