package com.wjq.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName : ApplicationContextUtil
 * @Description : 手动获取bean的工具类
 * @Author : CJH
 * @Date: 2020-08-19 22:13
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}

