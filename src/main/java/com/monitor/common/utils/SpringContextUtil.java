package com.monitor.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    /**
     * application context.
     */
    private static ApplicationContext ac;

    /**
     * set application context.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setApplicationContextStatic(applicationContext);
    }

    /**
     * set ApplicationContext.
     * @param applicationContext ApplicationContextAware.
     */
    public static void setApplicationContextStatic(final ApplicationContext applicationContext) {
        SpringContextUtil.ac = applicationContext;
    }

    /**
     * get bean.
     * @param <T> generic type.
     * @param cls cls.
     * @return bean.
     */
    public static <T> T getBean(Class<T> cls) {
        return ac.getBean(cls);
    }
}
