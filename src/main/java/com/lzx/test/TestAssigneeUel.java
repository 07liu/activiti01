package com.lzx.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * *
 *
 * @author: lzx@4229303
 * @create: 03-29
 **/


public class TestAssigneeUel {


    /**
     * Uel表达式流程
     * 流程部署
     */
    @Test
    public void testDeployment(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment()
                .name("TravelApplicationForm-Uel")
                .addClasspathResource("bpmn/evection-uel.bpmn")
                .deploy();

        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    /**
     *
     * 启动时需要设置负责人
     * 启动该流程
     */

    @Test
    public void startAssigneeUel(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> map = new HashMap<>();

        map.put("assignee0","Ame");
        map.put("assignee1","Maybe");
        map.put("assignee2","Chalice");
        map.put("assignee3","Fy");

        runtimeService.startProcessInstanceByKey("myEvection1",map);

        System.out.println(processEngine.getName());
    }
}
