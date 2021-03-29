package com.lzx.test;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * *
 *
 * @author: lzx@4229303
 * @create: 03-29
 **/


public class MyTaskListener implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {


        if ("CreateApply".equals(delegateTask.getName())
                &&"create".equals(delegateTask.getEventName())) {
            delegateTask.setAssignee("Ame");
        }
    }
}
