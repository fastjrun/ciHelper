package com.fastjrun.task;

import java.util.concurrent.ExecutorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fastjrun.common.TaskException;

public abstract class BaseTask {
    protected final Log log = LogFactory.getLog(this.getClass());
    
    @Value("${baseTask.semaphoreTtotal}")
    protected int semaphoreTtotal;

    @Autowired     
    protected ExecutorService executorService;

    public abstract void process() throws TaskException;

    public static void main(String[] args) {
        String taskName = args[0];
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        BaseTask baseTask = (BaseTask) appContext.getBean(taskName);
        baseTask.process();
        ((ClassPathXmlApplicationContext) appContext).close();
    }

}
